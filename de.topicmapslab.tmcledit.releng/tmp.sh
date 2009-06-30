#/opt/eclipse3.5/eclipse -nosplash -application org.eclipse.equinox.p2.director -metadataRepository file:/home/mai00ckx/eclipse.build/buildRepo -artifactRepository file:/home/mai00ckx/eclipse.build/buildRepo -installIU de.topicmapslab.tmcledit.application_feature.feature.group -destination /tmp/onotoa/ -profile Onotoa -profileProperties org.eclipse.update.install.features=true -bundlepool /tmp/onotoa/ -p2.os linux -p2.ws gtk -p2.arch x86 -roaming -vmargs -Declipse.p2.data.area=/tmp/onotoa/p2

java -jar /opt/eclipse3.5/plugins/org.eclipse.equinox.launcher_1.0.200.v20090520.jar -application org.eclipse.ant.core.antRunner \ 
    -buildfile /opt/eclipse3.5/plugins/org.eclipse.pde.build_3.5.0.v20090527-1800/scripts/package.xml \
    -DpackagingInfo=.