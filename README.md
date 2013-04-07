# Forge Maven Plugin project

## Summary

The Forge Maven Plugin provides a clean Maven integration with Forge.
You can install addons into a specific repository during build time, thus avoiding the need to store addons JARs in your SCM.

## Install

Add the following to your pom.xml:

      <plugin>
        <groupId>org.jboss.forge</groupId>
        <artifactId>forge-maven-plugin</artifactId>
        <version>1.0.0.Alpha2</version>
        <executions>
           <execution>
              <id>deploy-addons</id>
              <phase>prepare-package</phase>
              <goals>
                 <goal>addon-install</goal>
              </goals>
              <inherited>false</inherited>
              <configuration>
                 <addonRepository>${basedir}/addon-repository</addonRepository>
                 <addonIds>
                    <addonId>org.jboss.forge:addon-manager,${version.forge}</addonId>
                    <addonId>org.jboss.forge:maven,${version.forge}</addonId>
                    <addonId>org.jboss.forge:projects,${version.forge}</addonId>
                 </addonIds>
              </configuration>
           </execution>
        </executions>
      </plugin>

Where: 

    version.forge
    
is a maven property with the desired forge version (E.g. 2.0.0.Alpha2)


## Get the code

The easiest way to get started with the code is to [create your own fork](http://help.github.com/forking/),
and then clone your fork:

    $ git clone git@github.com:<you>/forge-maven-plugin.git
    $ cd forge-maven-plugin
    $ git remote add upstream git://github.com/forge/forge-maven-plugin.git

At any time, you can pull changes from the upstream and merge them onto your master:

    $ git checkout master               # switches to the 'master' branch
    $ git pull upstream master          # fetches all 'upstream' changes and merges 'upstream/master' onto your 'master' branch
    $ git push origin                   # pushes all the updates to your fork, which should be in-sync with 'upstream'

The general idea is to keep your 'master' branch in-sync with the
'upstream/master'.

## Building Forge Maven Plugin

To build _Forge Maven Plugin_ requires specific versions of Java and Maven.

This command will run the build:

    $ mvn clean verify

If you just want to check if things compiles/builds you can run:

    $ mvn clean verify -DskipTest=true

But *do not* push changes without having the new and existing unit tests pass!

## Contribute fixes and features

_Forge Maven Plugin_ is open source, and we welcome anybody that wants to
participate and contribute!

If you want to fix a bug or make any changes, please log an issue in
the [Forge JIRA](https://issues.jboss.org/browse/FORGE)
describing the bug or new feature and give it a component type of
`Maven Plugin`. Then we highly recommend making the changes on a
topic branch named with the JIRA issue number. For example, this
command creates a branch for the FORGE-1234 issue:

	$ git checkout -b FORGE-1234

After you're happy with your changes and a full build (with unit
tests) runs successfully, commit your changes on your topic branch
(with good comments). Then it's time to check for any recent changes
that were made in the official repository:

	$ git checkout master               # switches to the 'master' branch
	$ git pull upstream master          # fetches all 'upstream' changes and merges 'upstream/master' onto your 'master' branch
	$ git checkout FORGE-1234           # switches to your topic branch
	$ git rebase master                 # reapplies your changes on top of the latest in master
	                                      (i.e., the latest from master will be the new base for your changes)

If the pull grabbed a lot of changes, you should rerun your build with
tests enabled to make sure your changes are still good.

You can then push your topic branch and its changes into your public fork repository:

	$ git push origin FORGE-1234         # pushes your topic branch into your public fork of Forge Maven Plugin

And then [generate a pull-request](http://help.github.com/pull-requests/) where we can
review the proposed changes, comment on them, discuss them with you,
and if everything is good merge the changes right into the official
repository.

