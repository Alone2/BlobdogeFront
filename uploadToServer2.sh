# cd WebContent
# jar -cvf ROOT.war *
# echo ".war created"
scp $1 blobber.ch:/var/lib/tomcat8/webapps/ROOT.war
ssh alain@blobber.ch 'systemctl restart tomcat8' 
# rm ROOT.war
