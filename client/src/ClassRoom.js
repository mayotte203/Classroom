import React from 'react'

import './styles.css';

class ClassRoom extends React.Component
{
    state =
        {
            webSocket: null,
            stompClient: null,
            name: null,
            handRaised: false,
            success: null,
            students: []
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
    }

    studentStatusHandler(studentStatus){
        var students = this.state.students;
        var student = null;
        students.forEach(function(item, index) {
            if(item.name === studentStatus.name) {
                student = item;
                item.handRaised = studentStatus.handRaised;
            }
        });
        if(student === null){
            student = {name: studentStatus.name, handRaised: studentStatus.handRaised};
            students.push(student)
        }
        else{
            if(student.name === this.state.name){
                this.state.handRaised = student.handRaised;
            }
            if(studentStatus.disconnected){
                const index = this.state.students.indexOf(student);
                if (index > -1) {
                    this.state.students.splice(index, 1);
                }
            }
        }
        this.setState({students: students});
    }

    userTopicHandler(greetingJson) {
        this.setState({students: JSON.parse(greetingJson.body)});
        this.state.students.forEach(function(item, index) {
            if(item.name === this.state.name) {
                this.state.handRaised = item.handRaised;
            }
        }.bind(this));
    }

    componentDidMount() {
        this.state.name = localStorage.getItem("name")
        console.log(this.state.name)
        var Stomp = require('stompjs');
        var webSocket = new WebSocket('ws://127.0.0.1:8080/ws');
        var stompClient = Stomp.over(webSocket);
        stompClient.connect({}, (function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/user/topic/classroom', (greeting) => this.userTopicHandler(greeting));
            this.sendSignin();
            stompClient.subscribe('/topic/classroom', (greeting) => this.studentStatusHandler(JSON.parse(greeting.body)));
        }).bind(this));
        this.setState({stompClient: stompClient})
    }

    sendSignin() {
        this.state.stompClient.send("/app/signin", {}, JSON.stringify({'name': 'test123'}));
    }

    riseHandUp(){
        this.state.stompClient.send("/app/hand", {}, JSON.stringify({'name': localStorage.getItem("name"), 'handRaised': true, 'token': localStorage.getItem("token")}));
    }

    riseHandDown(){
        this.state.stompClient.send("/app/hand", {}, JSON.stringify({'name': localStorage.getItem("name"), 'handRaised': false, 'token': localStorage.getItem("token")}));
    }

    logout(){
        const logoutRequest = {name: this.state.name, token: localStorage.getItem("token")};
        fetch('http://127.0.0.1:8080/api/logout',
            {method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(logoutRequest)});
        localStorage.clear();
        this.props.history.push("/login");
    }

    render()
    {
        let button;
        if(this.state.handRaised) {
            button = <button onClick={() => this.riseHandDown()}>Raise hand down</button>;
        }
        else {
            button =<button onClick={() => this.riseHandUp()}>Raise hand up</button>;
        }
        return (
            <div className="classroom">
                <div className="navbar">
                    <ul>
                        <li>
                            <div className="dropdown">
                                <button className="dropbtn">Actions ▼</button>
                                <div className="dropdown-content">
                                    {button}
                                </div>
                            </div>
                        </li>
                        <li style={{float: "right"}}>
                            <div className="dropdown">
                                <button className="dropbtn">{this.state.name} ▼</button>
                                <div className="dropdown-content">
                                    <button onClick={() => this.logout()}>Logout</button>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
                <div className="class-members">
                    <h1>Classroom members</h1>
                    <table>
                        <tbody>{this.state.students.map(function(item, key) {
                            return (
                                <tr key = {key}>
                                    <td>{item.name}</td>
                                    <td>
                                        {item.handRaised &&
                                            <img src="hand.png" alt="альтернативный текст" style={{height: "20px"}}></img>
                                        }
                                    </td>
                                </tr>
                            )
                        })}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ClassRoom;