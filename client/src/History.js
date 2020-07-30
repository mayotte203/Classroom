import React from 'react'
import './bootstrap.min.css';
import './History.css'
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";

class HistoryForm extends React.Component
{
    constructor(props) {
        super(props);
        this.state = {
            startDate: new Date(),
            endDate: new Date(),
            name: '',
            action: '',
            ascending: true,
            history: []
        };
        this.handleActionChange = this.handleActionChange.bind(this);
        this.handleNameChange = this.handleNameChange.bind(this);
        this.handleStartDateChange = this.handleStartDateChange.bind(this);
        this.handleEndDateChange = this.handleEndDateChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleOrderSwitch = this.handleOrderSwitch.bind(this);
    }

    handleNameChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({ [name]: value });
    }

    handleActionChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({ [name]: value });
    }

    handleStartDateChange(date) {
        this.setState({
            startDate: date
        });
    }

    handleEndDateChange(date) {
        this.setState({
            endDate: date
        });
    }

    handleOrderSwitch(){
        this.setState({
            ascending: !this.state.ascending
        })
    }

    handleSubmit(event)
    {
        event.preventDefault();
        fetch('http://127.0.0.1:8080/api/history',
            {method: 'POST',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({name: this.state.name,
                action: this.state.action,
                startDate: this.state.startDate,
                endDate: this.state.endDate,
                ascending: this.state.ascending})})
            .then(response =>
                response.json().then(json => {
                    this.setState({history: json})
                })
            );
    }

    render()
    {
        let icon;
        if(this.state.ascending){
            icon = <img src="ASC.png" alt="" style={{height: "30px"}}></img>
        }
        else{
            icon = <img src="DSC.png" alt="" style={{height: "30px"}}></img>
        }
        return (
            <div>
                <form className="history-form" onSubmit={this.handleSubmit}>
                    <div className="form-row">
                        <div className="form-group col-md-4">
                            <label htmlFor="inputName">Name</label>
                            <input type="text" className="form-control" id="inputName" placeholder="Name" name="name" onChange={this.handleNameChange}/>
                        </div>
                        <div className="form-group col-md-3">
                            <label htmlFor="inputAction">Action</label>
                            <select value={this.state.value} id="inputAction" className="form-control" name="action" onChange={this.handleActionChange}>
                                <option value={''}>Any</option>
                                <option value={'hand up'}>Hand up</option>
                                <option value={'hand down'}>Hand down</option>
                                <option value={'login'}>Login</option>
                                <option balue={'logout'}>Logout</option>
                            </select>
                        </div>
                    </div>
                    <div className="form-row">
                        <div className="form-group col-md-3">
                            <label htmlFor="inputDate1">From</label>
                            <DatePicker id="inputDate1" className="form-control" selected={this.state.startDate}  onChange={this.handleStartDateChange}/>
                        </div>
                        <div className="form-group col-md-3">
                            <label htmlFor="inputDate2">To</label>
                            <DatePicker id="inputDate2" className="form-control" selected={this.state.endDate}  onChange={this.handleEndDateChange}/>
                        </div>
                        <div className="form-group col-md-3">
                            <button type="button" onClick={this.handleOrderSwitch}>{icon}</button>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary">Submit</button>
                </form>
                <table  class="table">
                    <tbody>{this.state.history.map(function(item, key) {
                        return (
                            <tr key = {key}>
                                <td>{item.name}</td>
                                <td>{item.action}</td>
                                <td>{item.date}</td>
                            </tr>
                        )
                    })}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default HistoryForm;