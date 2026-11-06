# Team Guide

How we work on this project. Follow these steps for every stage.

## Golden rules
1. Never push straight to `main`. Work on a branch and open a pull request.
2. Always pull `main` before starting.
3. Run the app before every push. Never push code that does not build.
4. Never commit `local.properties`, `build/` folders, or `.jks` files.
5. `ui/` may import from `data/`. `data/` must never import from `ui/`.

## Who does what
| Stage | Person | Branch | Reviewer |
|---|---|---|---|
| 1 | Frank | main (direct) | - |
| 2 | Shana | feature/viewmodel | Frank |
| 3 | Steve | feature/room | Daniella |
| 4 | Shana | feature/screens | Steve |
| 5 | Daniella | feature/data-integration | Steve |
| 6 | Frank | release/v1.0 | Daniella |

## Your steps after cloning
1. Accept the GitHub invite, then clone: Android Studio -> File -> New -> Project from Version Control -> paste the repo URL -> Clone.
2. Pull the latest: Git -> Pull.
3. Make your branch: bottom-right branch button -> New Branch -> type your branch name (e.g. `feature/viewmodel`) -> keep "Checkout branch" ticked -> Create.
4. Do your stage's work. Then Sync: File -> Sync Project with Gradle Files.
5. Run the app once and check it builds and works.
6. Commit: Git -> Commit, with a clear message like "Add TenantViewModel with LiveData".
7. Push: Git -> Push.
8. Open a pull request on github.com: base `main`, compare `your-branch`. Title: `Stage N: what you did`. Assign your reviewer.
9. Your reviewer approves it. Frank merges.
10. Tell the group, then everyone does Git -> Pull to get the new code.

## If it breaks after pulling
File -> Sync Project with Gradle Files, then Build -> Rebuild Project.