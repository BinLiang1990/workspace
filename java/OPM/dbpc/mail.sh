file='dbpc.log'
recipients='team_ops@vobile.cn'
idc="WASU"
url="192.168.16.188:8080"
sendMail() {
    mail="{\"type\":\"mail\",\"sender\":\"wang_lin@vobile.cn\",\"recipients\":[\"$recipients\"],\"subject\":\"$idc Unregistered Alarm Spots\",\"content\":\"$content\"}"
    curl -X POST -H "Content-Type: application/json" -d "$mail" http://$url/vns_server/notification/add
}
while true
do
    awk '$8 != "out"{print $6, $7, $8}' $file | sort -u -o $file
    str=$(awk 'NR==2' $file)
    content=$(awk '{printf $0"\\n" }' $file)
    if [ ${#str} -gt 0 ];then
        sendMail
    fi
    sleep 86400
done