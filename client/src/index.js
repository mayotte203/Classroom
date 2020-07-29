import React from 'react';
import ReactDOM from 'react-dom';
import { Route, BrowserRouter, Switch} from 'react-router-dom'
import './index.css';
import LoginForm from "./LoginForm";
import ClassRoom from "./ClassRoom";
import NotFound from "./NotFound";
import HistoryForm from "./History";
ReactDOM.render(
  <React.StrictMode>
      <BrowserRouter>
          <Switch>
              <Route path="/login" component={LoginForm} />
              <Route path="/members" component={ClassRoom} />
              <Route path="/history" component={HistoryForm} />
              <Route component={NotFound} />
          </Switch>
      </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);