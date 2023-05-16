# Getting started

## Introduction

This project template is intended as a project starter, using the Gradle project structure and build system, but providing a slightly more complete starting point than the **New Project** wizard dialog in IntelliJ IDEA provides.

This document is intended by used as a guide for the initial setup and ongoing configuration of the project. We recommend completing each the [configuration steps](#configuration-steps) below, in order: reading the step carefully, and then completing the specified task (if any). Remember to remove/modify any associated `TODO` comments in the project files as you complete each task. 

## Configuration steps

1. ### Verify JDK version

    The JDK for the project is initially set to JDK 11; this can be changed by modifying the value assigned to the `javaVersion` property in [`gradle.properties`](gradle.properties).  

    **_Task_**    
    Verify (and change, if necessary) the _project SDK_ to the same JDK version set in `gradle.properties`; this setting is found in the **Project Structure** dialog (accessed via the **File/Project Structure** menu command), in the **Project Settings/Project** screen. The _language level_ (set in the same screen as the SDK) should be set to "SDK default".

2. ### Set root project name

    Initially, the root project is called `replace-me`. However, this root project name will become part of the name of any JAR files built by the project; thus, it should be changed to something more relevant to your specific project. This is done by editing the value assigned to `rootProject.name` in the [`settings.gradle`](settings.gradle) file.

   <a id="root-project-name"></a>**_Task_**    
    Modify the value of the `rootProject.name` property in the [`settings.gradle`](settings.gradle) file, replacing `replace-me` with a `lower-spinal-case` form (all lowercase, with multiple words separated by dashes) of your project or application name. (There's a `TODO` comment for this change in that file.) Make sure to keep the existing single quotes.

3. ### Choose a base package name and create a main class 

   <a id="base-package-name"></a>In most Java projects, all Java code is located in a _base package_, or in subpackages of that base package. Package names are always in lowercase, with only alphanumeric characters and underscores allowed, except for the period character (`.`) between the hierarchical components of the name. The base package name typically starts with the company or development organization's domain name, but with the components reversed. For example, we might start our base package with `com.tlglearning`, while a project being developed at Amazon might have a base package name that starts with `com.amazon`. We usually append one or more additional components, identifying the group within the organization and/or the project itself. For example, if we were going to develop a Blackjack game in this training program, we might use a base package name of `com.tlglearning.sde.blackjack`. 

   (Note that the _base package_ is not the same as the _default package_: the latter is no package at all; placing our classes or interfaces directly in the default package is generally not considered a good practice.)

    Whatever you select as a base package name, make a note of it; you'll not only use it now, but in the step that follows. 

    <a id="main-class"></a>**_Task_**    
    Create a new Java class, located in the base package you've decided on, all inside the `src/main/java` directory. (You can do this by creating the package first, and then the class, or by creating the class using the fully qualified name.) This class will serve as your application's entry point (or one of them); that is, it will include a `public static void main(String[] args)` method. Thus, the simple name of the class should be something appropriate to that purpose---e.g. `Main` or `Application`. 

4. ### Complete the Maven coordinates

    Most Gradle projects have Maven _coordinates,_ enabling the project's build artifacts to be cached automatically (for reference by other builds, among other things) and optionally deployed to local or remote Maven repositories. There are (at least) three components to Maven coordinates:

    * `groupId`
    * `artifactId`
    * `version`

    #### `groupId`

    This project starter is set up so that you can set the `groupId` by assigning a value to the `group` property in the [`gradle.properties`](gradle.properties) file. The value of this property should follow the same rules as a Java package name&mdash;all lowercase, without dashes or spaces, and with periods separating the components. In fact, the `groupId` of a project is usually the first 2&ndash;3 components of the [base package name](#base-package-name) used in the project; that's the approach you'll follow.

    **_Task_**    
    Change the value of the `group` property in the [`gradle.properties`](gradle.properties) file, replacing `replace.me` with the first 2 or 3 components of the [base package name](#base-package-name). (There's a `TODO` comment for this change in that file).

       #### `artifactId`

    [Setting the `rootProject.name` property](#root-project-name) in the build also sets the `artifactId` portion of the Maven coordinates; there's no need to make any changes to `gradle.properties` for this. 

    #### `version`

    The `version` of the Maven coordinates is also set in [`gradle.properties`](gradle.properties). It's initially set to `1.0.0-SNAPSHOT`, and there's no need to modify it at the start of the project, unless you want to start at a different version number. However, it should be updated as your project progresses, according to your project's versioning scheme. (This value will automatically be included as part of the name of JAR artifacts produced by the build.)

5. ### Set the main class of the JAR artifact

    The project is configured to use the `com.github.johnrengelman.shadow` plug-in (see line 2 of [`build.gradle`](build.gradle)) to create a runnable JAR from the root project, with all runtime dependencies packaged in the same JAR. This plug-in uses the `mainClass` property of the `application` plug-in to set the corresponding attribute of the JAR's manifest. 

    **_Task_**    
    Set the main class of the JAR artifacts that will be built by modifying the value of the `mainClass` property in [`gradle.properties`](gradle.properties), replacing `replace.me.project.Main` with the fully qualified name of your [main class](#main-class). (There's a `TODO` comment for this change in that file.)       

6. ### Review source and resource folder structure

   Gradle has a default directory structure&mdash;shown below&mdash;for the source, test source, resource, and test resource folder. IntelliJ automatically uses this structure in a Gradle project; there's no need to set or change it. However, if you don't have much Gradle experience, it's a good idea to review this structure in your project, and remember to follow it when adding new code or other content to your project.

    * `src/`
        * `main/`
            * `java/` (source folder)
            * `resources/` (resource folder)
        * `test/`
            * `java/` (test source folder)
            * `resources/` (test resource folder)

   Most importantly, please remember:

    1. When building your application, the only `.java` files that will be compiled are those located in the `src/main/java` directory (or a subdirectory of that directory, according to the package structure).

       (When building tests, the `src/test/java` files will also be compiled.)

    2. At run time&mdash;whether launching from IntelliJ, from the command line using a `.jar` file, or from the command line using the `.class` files directly&mdash;the only non-Java files in your project that will be accessible on the class path are those located in the `src/main/resources` directory (or a subdirectory of that directory).

       (When running tests, files from `src/test/resources` will also be accessible on the class path.)

   Note: Since Git tracks only files and their paths relative to the repository root, but not directories themselves, each of these directories initially contains a `.keep` file, so that the directory structure is preserved in Git. If/when files are added to any given one of these directories, the `.keep` file in that directory can be deleted.

7. ### Review dependencies

    We do not recommend that dependencies be incorporated directly as JAR files in the project, unless those JARs are _not_ otherwise accessible in the Maven Central repository, or another accessible Maven repository. Instead, whenever possible, project dependencies should be declared in the `dependencies` task of [`build.gradle`](build.gradle) (initially starting at line 26), using the appropriate Gradle methods to specify each dependency's Maven coordinates. 

    Note that JUnit5 is already included in the `dependencies` task. (There are also instructions in the comments of that task for using JUnit4 instead of JUnit5.) Here are just a few more frequently used libraries, with the Gradle statement to be added to `dependencies` for the latest release (as of 2023-05-03) of each:

    | Library | Statement in `dependency`                                             | 
    |:--------|:----------------------------------------------------------------------|
    | Gson    | `implementation 'com.google.code.gson:gson:2.10.1'`                   |
    | Jackson | `implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.0'` |
    | Jansi   | `implementation 'org.fusesource.jansi:jansi:2.4.0'`                   |

8. ### Review version control exclusions and inclusions

    The `.gitignore` file included in this project repository is specifically intended for use with either a Gradle or Maven project. The list of included/excluded files and directories is close to&mdash;but not exactly the same as&mdash;the corresponding list for a native IntelliJ project. If at some moment the project is converted to a native IntelliJ project, some files currently being ignored in the `.idea` directory should be tracked in version control. Do this by replacing the line (initially lines 4--5) reading 

    ```gitignore
    *.iml
    .idea/
    ```

    with the following:

    ```gitignore
    .idea/*
    !.idea/*.iml
    !.idea/artifacts/
    !.idea/libraries/
    !.idea/modules.xml
    !.idea/misc.xml
    ```

    These changes should be made by one team member, on one branch of the repository, then propagated (to remotes, other branches, other team members' local clones, etc.) via the usual Git mechanisms.

9. ### Review build tasks

    In a project it recognizes as using Gradle, IntelliJ automatically leverages Gradle for the operations found under the **Build** menu. However, a more complete set of build tasks is available under _Tasks_ in IntelliJ's **Gradle** tool window. Here are some of the tasks that can be executed in this project:

    * _application_

        * _run_
  
            Loads the class specified (in [`build.gradle`](build.gradle)) in the `application.mainClass` property, and invokes its `main(String[])` method.

    * _build_

        * _assemble_

            Compiles code in `src/main/java`, processes resources in `src/main/resources`, generates HTML from the bytecode and the Javadoc comments in the source code, and packages the results into JAR files in `build/libs`.

        * _build_

            Compiles code in `src/main/java` and processes resources in `src/main/resources`, with output in `build/classes/java`.

        * _clean_

            Deletes the `build` directory&mdash;including all previously compiled `.class` files, copied resource files, etc. 

        * _jar_

            Compiles code in `src/main/java`, processes resources in `src/main/resources`, and packages the results into a JAR file in `build/libs`.

        * _javadocJar_

            Compiles code in `src/main/java`, generates HTML from the bytecode and the Javadoc comments in the source code, and packages the results into a JAR file in `build/libs`.
    
    * _documentation_

        * _javadoc_

            Compiles code in `src/main/java`, and generates HTML from the bytecode and the Javadoc comments in the source code, with results generated into `build/docs/javadoc`.

    * _shadow_

        * _shadowJar_

            Compiles code in `src/main/java`, processes resources in `src/main/resources`, and packages the results (including dependencies) into a JAR file in `build/libs`.

    * _verification_

        * _test_

           Compiles code in `src/main/java` and `src/test/java`, processes resources in `src/main/resources` and `src/test/resources`, with output in `build/classes/java`, and launches JUnit to run all test methods in `build/classes/java/test`.
