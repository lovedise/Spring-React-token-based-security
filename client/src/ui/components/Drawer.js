import React from 'react';
import Drawer from 'material-ui/Drawer';
import MenuItem from 'material-ui/MenuItem';
import RaisedButton from 'material-ui/RaisedButton';

export default class UtilDrawer extends React.Component {
  constructor(props) {
    super(props);
    this.state = {open: props.open}
  }

  handleToggle = () => this.setState({open : !this.state.open});

  render() {
    return (
      <div>
        <Drawer
          docked={false}
          width={200}
          open={this.props.open}
          onRequestChange={this.props.drawerHandler}
        >
          <MenuItem onTouchTap={this.props.drawerHandler}>Menu Item</MenuItem>
          <MenuItem onTouchTap={this.props.drawerHandler}>Menu Item 2</MenuItem>
        </Drawer>
      </div>
    )
  }
}
