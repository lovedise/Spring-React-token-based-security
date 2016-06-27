/**
 * Created by namee on 2016. 6. 22..
 */

import React from 'react';
import AppBar from '../components/AppBar';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import {deepOrange500} from 'material-ui/styles/colors';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Login from '../components/login';
import Drawer from '../components/Drawer';

const styles = {
    container: {
        textAlign: 'center',
    },
};

const muiTheme = getMuiTheme({
    palette: {
        accent1Color: deepOrange500,
    },
});


class Main extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.state = ({drawerOpen : false})
    }

    drawerHandler = () => {
      this.setState({
        drawerOpen : !this.state.drawerOpen
      })
    };

    render() {
        return (
            <MuiThemeProvider muiTheme={muiTheme}>
              <div style={styles.container}>
                <Drawer open={this.state.drawerOpen} drawerHandler={this.drawerHandler}/>
                <AppBar drawerHandler={this.drawerHandler}/>
                <Login />
              </div>
            </MuiThemeProvider>

        );
    }
}

export default Main;
