package device;

import java.util.HashMap;

//import terminal.Terminal;	//found in project: terminalSerial


public class DeviceController {
	HashMap<Byte, Device8080> inputDevices;
	HashMap<Byte, Device8080> outputDevices;
	HashMap<Byte, Device8080> statusDevices;
	private String errMessage;

	public DeviceController() {
		inputDevices = new HashMap<Byte, Device8080>();
		outputDevices = new HashMap<Byte, Device8080>();
		statusDevices = new HashMap<Byte, Device8080>();
		//TODO - adjust for serial terminal
//		Terminal console = new Terminal();

//		Byte deviceAddress = console.getAddressIn();
//		if (deviceAddress != null) {
//			inputDevices.put(deviceAddress, console);
//		}// if in device
//
//		deviceAddress = console.getAddressOut();
//		if (deviceAddress != null) {
//			outputDevices.put(deviceAddress, console);
//		}// if in device
//
//		deviceAddress = console.getAddressStatus();
//		if (deviceAddress != null) {
//			statusDevices.put(deviceAddress, console);
//		}// if in device
			// TODO Auto-generated constructor stub
	}// Constructor DeviceController()

	public void byteToDevice(Byte address, Byte value) {
		if (!inputDevices.containsKey(address)) {
			errMessage = String.format("Bad address %02X for byteToDevice operation", address);
			System.err.println(errMessage);
		} else {
			Device8080 device = inputDevices.get(address);
			device.byteFromCPU(address, value);
		}// for
	}// byteToDevice

	public Byte byteFromDevice(Byte address) {
		Device8080 device ;
		if(inputDevices.containsKey(address)){
			device = inputDevices.get(address);
		}else if(statusDevices.containsKey(address)){
			device = statusDevices.get(address);
		}else {
			errMessage = String.format("Bad address %02X for byteToDevice operation", address);
			System.err.println(errMessage);
			return null;
		}//if
			return device.byteToCPU(address);
	}// byteFromDevice

}// class DeviceController
