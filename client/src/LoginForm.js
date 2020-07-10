import React from 'react'

class LoginForm extends React.Component
{
    constructor(props)
    {
        super(props);
        if(localStorage.getItem("isLogedIn"))
        {
            this.props.history.push("/members");
        }
        this.state =
            {
                name: '',
                success: null
            };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event)
    {
        const target = event.target;
        const value = target.value;
        const name = target.name;

        this.setState({ [name]: value });
        return true;
    }

    handleSubmit(event)
    {
        const loginRequest = {name: this.state.name};

        event.preventDefault();
        fetch('http://127.0.0.1:8080/api',
            {method: 'POST',
                 mode: 'cors',
                 headers: {
                     'Content-Type': 'application/json'
                 },
                 body: JSON.stringify(loginRequest)})
            .then(response =>
                response.json().then(json => {
                    localStorage.setItem("name", json.name);
                    localStorage.setItem("token", json.token);
                    localStorage.setItem("isLogedIn", json.isSuccessful);
                    if(localStorage.getItem("isLogedIn")) {
                        this.props.history.push("/members");
                    }
                })
            );

    }

    render()
    {
        return (
            <div className="login-form">
                <h1>Sign In</h1>
                <p>
                    Please enter your name.
                </p>
                {!this.state.success &&
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label>Your Name</label>
                        <input type="text" className="form-control" placeholder="John Smith" name="name" required onChange={this.handleChange} />
                    </div>
                    <button type="submit" className="btn btn-primary">Sign In</button>
                </form>}
            </div>
        );
    }
}

export default LoginForm;