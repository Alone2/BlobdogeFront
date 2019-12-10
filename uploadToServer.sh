# cd WebContent
# jar -cvf ROOT.war *
# echo ".war created"
scp -P 13122 $1 pi@preview.blobber.ch:/var/lib/tomcat8/webapps/ROOT.war
ssh -p 13122 pi@preview.blobber.ch 'sudo systemctl restart tomcat8' 
# rm ROOT.war
