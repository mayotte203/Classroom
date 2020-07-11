import React from 'react'
class ClassRoom extends React.Component
{
    state =
        {
            webSocket: null,
            stompClient: null,
            name: null,
            success: null,
            students: null
        };

    constructor(props)
    {
        super(props);
        if(localStorage.getItem("token") === null) {
            this.props.history.push("/login");
        }
        if(localStorage.getItem("token") === "null") {
            this.props.history.push("/login");
        }
        //this.userTopicHandler = this.userTopicHandler.bind(this);
    }

    userTopicHandler(greeting) {
        this.setState({students: JSON.parse(greeting.body)});
        console.log(this.state.students[0].name);
    }

    componentDidMount() {
        var Stomp = require('stompjs');
        var webSocket = new WebSocket('ws://127.0.0.1:8080/ws');
        var stompClient = Stomp.over(webSocket);
        stompClient.connect({}, (function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                console.log(JSON.parse(greeting.body).content);
            });
            stompClient.subscribe('/user/topic/greetings', (greeting) => this.userTopicHandler(greeting));
        }).bind(this));
        this.setState({stompClient: stompClient})
    }


    sendHello() {
        this.state.stompClient.send("/app/hello", {}, JSON.stringify({'name': 'test123'}));
    }

    sendSignin() {
        this.state.stompClient.send("/app/signin", {}, JSON.stringify({'name': 'test123'}));
    }

    render()
    {
        return (
            <div className="classroom">
                <h1>Classroom</h1>
                <button onClick={() => this.sendHello()}>Hello</button>
                <button onClick={() => this.sendSignin()}>Signin</button>
            </div>
        );
    }
}

export default ClassRoom;