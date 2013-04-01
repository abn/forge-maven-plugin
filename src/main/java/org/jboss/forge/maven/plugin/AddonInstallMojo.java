/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.maven.plugin;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.jboss.forge.addon.manager.AddonManager;
import org.jboss.forge.addon.manager.impl.AddonManagerImpl;
import org.jboss.forge.container.Forge;
import org.jboss.forge.container.ForgeImpl;
import org.jboss.forge.container.addons.AddonId;
import org.jboss.forge.container.impl.AddonRepositoryImpl;
import org.jboss.forge.maven.dependencies.FileResourceFactory;
import org.jboss.forge.maven.dependencies.MavenContainer;
import org.jboss.forge.maven.dependencies.MavenDependencyResolver;

/**
 * Goal which installs addons to a specified directory
 *
 * @goal addon-install
 *
 * @phase prepare-package
 */
public class AddonInstallMojo extends AbstractMojo
{

   /**
    * Addon repository file location
    *
    * @parameter property="${forge.repository}"
    * @required
    */
   private File addonRepository;

   /**
    * Addon IDs to install
    *
    * @parameter property="${forge.addonIds}"
    * @required
    */
   private String[] addonIds;

   @Override
   public void execute() throws MojoExecutionException, MojoFailureException
   {
      Forge forge = new ForgeImpl();
      if (!addonRepository.exists())
      {
         addonRepository.mkdirs();
      }
      forge.setRepositories(AddonRepositoryImpl.forDirectory(forge, addonRepository));
      AddonManager addonManager = new AddonManagerImpl(forge, new MavenDependencyResolver(
               new FileResourceFactory(), new MavenContainer()));

      for (String addonId : addonIds)
      {
         AddonId id = AddonId.fromCoordinates(addonId);
         addonManager.install(id).perform();
      }
   }
}
