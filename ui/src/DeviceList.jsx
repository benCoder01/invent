import * as React from "react";
import { TableHead, Table, TableCell, TableBody, TableRow } from "@material-ui/core";

export class DeviceList extends React.PureComponent {
    state = {
        devices: []
    }

    async componentDidMount() {
        const result = await fetch('http://localhost:8080/devices');
        const devices = await result.json();
        console.log(devices)
        this.setState({ devices: devices });
    }

    render() {
        return (
            <Table>
                <TableHead>
                    <TableCell>ID</TableCell>
                    <TableCell>Typ</TableCell>
                    <TableCell>Name</TableCell>
                    <TableCell>Location</TableCell>
                    <TableCell>Bemerkung</TableCell>
                </TableHead>
                <TableBody>
                    {this.state.devices.map(device => (
                        <TableRow>
                            <TableCell>{device.id}</TableCell>
                            <TableCell>{device.type.name}</TableCell>
                            <TableCell>{device.name}</TableCell>
                            <TableCell>{device.location.name}</TableCell>
                            <TableCell>{device.comment}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        );
    }
}