# gist-uploader

This project implements a CLI to upload a **Gist** to your GitHub account, also it shows a list of all **_Gist_** files

### Lint
The linter we use is _kotlinter_ which is integrated via gradle
+ To check your code run the following task: `./gradlew lintKotlin`
+ To format your code run the following task: `./gradlew formatKotlin`

### Git Hooks
This project also use a git hook that is run before every push. For now this hook only runs the lint though the whole project, it will try to fix the code should it be not properly formatted.

To set it up please ensure the path is set with the following ```git config core.hooksPath .githooks```