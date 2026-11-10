# Lab 12 - Threads and locks: answers

## Programs (right-click > Run As > Java Application)
Arguments (Run Configurations > Arguments > Program arguments): `<nbAccounts> <nbTransactions>`

| Program            | Part | What it does |
|--------------------|------|--------------|
| `App`              | a    | Fixed thread pool (size = number of CPU cores) |
| `AppCachedPool`    | a    | Cached thread pool |
| `AppLocks`         | b, c | Locks instead of synchronized, sender then receiver -> deadlock (stopped after 15 s) |
| `AppOrderedLocks`  | d    | Locks in ascending account-id order -> no deadlock |

Shared code is in `Simulation.java`.

## a. Fixed vs cached thread pool
Measured on a 2-core machine (your numbers will differ, the trend should not):

| Case                                   | Fixed pool | Cached pool |
|----------------------------------------|-----------:|------------:|
| 1000 accounts, 1,000,000 transactions  | ~550 ms    | ~3600 ms    |
| 10 accounts, 1,000,000 transactions    | ~530 ms    | ~3500 ms    |

**Observation:** the fixed pool is several times faster. Each transaction is a tiny task, so
the cost is dominated by thread management, not by the work. The cached pool creates a new
thread whenever all existing threads are busy, so with a flood of one million tasks it ends up
creating thousands of threads: thread creation, memory per thread and context switching cost
far more than the transactions themselves. The fixed pool reuses a few threads (one per core)
that pull tasks from a queue.
With 10 accounts there is more contention (many transactions compete for the same few account
locks) than with 1000 accounts; with a cached pool and many threads, contention adds even more
waiting. A cached pool suits a small number of short-lived bursts of tasks, a fixed pool suits
a large number of CPU-bound tasks.

Note: the starting code used `newFixedThreadPool(nbTransactions)`, i.e. one thread per
transaction (one million threads), which removes the benefit of a pool. `App` now uses the
number of CPU cores.

## b. synchronized replaced by locks
- `CryptoAccount`: each account has its own `ReentrantLock`; `addToBalance` and
  `retrieveFromBalance` use `lock()` / `unlock()` in `try/finally` instead of `synchronized`.
- `LockTransaction`: locks the sender, then the receiver, so the check of the balance and the
  transfer happen atomically.

## c. What could go wrong?
**Deadlock.** Two transactions in opposite directions:
- Thread 1: A -> B locks A, then waits for B
- Thread 2: B -> A locks B, then waits for A

Each thread holds the lock the other one needs and both wait forever (circular wait).
`AppLocks` shows it: the program never finishes, so it is stopped after a 15 s timeout.

## d. Resource ordering
`OrderedLockTransaction` sorts the two accounts by id and always locks the smaller id first,
whatever the direction of the payment. A -> B and B -> A both lock A then B, so a circular wait
cannot happen. `AppOrderedLocks` finishes 1,000,000 transactions in well under a second and
the total money stays the same.
