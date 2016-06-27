/**
 * Created by namee on 2016. 6. 22..
 */
import React from 'react';
import AppBar from 'material-ui/AppBar';
import IconButton from 'material-ui/IconButton';
import IconMenu from 'material-ui/IconMenu';
import MenuItem from 'material-ui/MenuItem';
import MoreVertIcon from 'material-ui/svg-icons/navigation/more-vert';
import NavigationClose from 'material-ui/svg-icons/navigation/close';

class AppBarExampleIconMenu extends React.Component{
  constructor(props){
    super(props);
    console.log(this.props);
  }

  touch() {
    this.props.drawerHandler();
    console.log('11');
  }

  render() {
    return (
      <AppBar
        title="Title"
        onLeftIconButtonTouchTap={this.touch.bind(this)}
        // iconElementLeft={<IconButton><NavigationClose /></IconButton>}
        iconElementRight={
        <IconMenu
          iconButtonElement={
            <IconButton><MoreVertIcon /></IconButton>
          }
          targetOrigin={{horizontal: 'right', vertical: 'top'}}
          anchorOrigin={{horizontal: 'right', vertical: 'top'}}
        >
        <MenuItem primaryText="Refresh" />
            <MenuItem primaryText="Help" />
            <MenuItem primaryText="Sign out" />
            </IconMenu>
        }
      />
    )
  }
}

export default AppBarExampleIconMenu;
