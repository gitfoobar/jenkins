import hudson.*;
import hudson.model.*;
import hudson.plugins.git.*;
import hudson.plugins.git.GitAPI;
import org.eclipse.jgit.transport.*;
import org.eclipse.jgit.api.Status;

threshold = Result.SUCCESS


void de_print(msg) {
  manager.listener.logger.println(">>")
  manager.listener.logger.println(msg)
}

void post_processing(manager) {
    build = manager.build
    gitSCM = build.getProject().getScm();
    gitExe = gitSCM.getGitExe(build.getBuiltOn(), manager.listener)
    environment= build.getEnvironment(manager.listener)

    de_print(gitSCM)
    de_print(gitExe)
    de_print(environment)
    de_print("build.getWorkspace() = ${build.getWorkspace()}")
    de_print("build.getWorkspace().class = ${build.getWorkspace().class}")

    git = new hudson.plugins.git.GitAPI(gitExe, build.getWorkspace(), manager.listener, environment)
    de_print(git)

    branch_name = manager.build.getBuildVariables()['BRANCH']
    de_print("branch name = ${branch_name}")
    remote = gitSCM.getRepositoryByName('origin')

    from_branch = "feature/integration/${branch_name}"
    to_branch = "feature/pause/${branch_name}"
    de_print("from_branch  = ${from_branch}")
    de_print("to_branch  = ${to_branch}")

    /*
    if(build.getResult().isWorseThan(threshold) ) {
      git.launchCommand("checkout", "-b","${from_branch}", "origin/${from_branch}")
      git.push(remote, "${from_branch}:${to_branch}")
      git.push(remote, ":${from_branch}")
    }
    */
}