import * as React from "react";
import Drawer from "./Drawer";
import { DeviceList } from "./DeviceList";

export class App extends React.Component {
    render() {
        return (
            <Drawer>
                <DeviceList />
            </Drawer>
        )
    }
}