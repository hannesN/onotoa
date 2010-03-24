for i in `ls | grep -v repo`; do  cd $i && zip -r ../onotoa-1.1.1-$i.zip onotoa/* && cd ..; done
