if [ $(docker inspect -f '{{.State.Running}}' "selenoid") = "true" ];
    then
        echo "Selenoid is already running";
        docker ps --filter "name=^selenoid$" --format '{{.Ports}}'
else
    curl -s https://aerokube.com/cm/bash | bash \
    && ../../../cm selenoid start --port 4444 --vnc
        if [ $(docker inspect -f '{{.State.Running}}' "selenoid") = "true" ];
        then
        echo "Selenoid successfully started";
        docker ps --filter "name=^selenoid$" --format '{{.Ports}}'
        else
            echo  "Selenoid launch is failed"
    fi
fi