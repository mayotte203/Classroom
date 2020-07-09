import React from 'react'
class ClassRoom extends React.Component
{
    state =
        {
            webSocket: null,
            name: null,
            success: null
        };

    constructor(props)
    {
        super(props);
        if(!localStorage.getItem("isLogedIn")) {
            this.props.history.push("/login");
        }
    }

    componentDidMount() {
        /*this.state.webSocket = new WebSocket('ws://127.0.0.1:8080/gs-guide-websocket');
        this.state.webSocket.onopen = () => {
            console.log('connected')
        }*/
        var Stomp = require('stompjs');
        var socket = new WebSocket('ws://127.0.0.1:8080/gs-guide-websocket');
        var stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
        });
    }


    render()
    {
        return (
            <div className="classroom">
                <h1>Classroom</h1>
            </div>
        );
    }
}

export default ClassRoom;