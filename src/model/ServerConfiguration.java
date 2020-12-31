package model;

import java.io.Serializable;

public class ServerConfiguration implements Serializable {
    static final long serialVersionUID = 3L;
    String rmiServerName = "";
    int rmiPort = 0;
    int stringServerPort = 0;
    int numericServerPort = 0;
    int code  = 0;

    public void setRmiServerName(String rmiServerName) {
        this.rmiServerName = rmiServerName;
    }

    public void setRmiPort(int rmiPort) {
        this.rmiPort = rmiPort;
    }

    public void setStringServerPort(int stringServerPort) {
        this.stringServerPort = stringServerPort;
    }

    public void setNumericServerPort(int numericServerPort) {
        this.numericServerPort = numericServerPort;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRmiServerName() {
        return rmiServerName;
    }

    public int getRmiPort() {
        return rmiPort;
    }

    public int getStringServerPort() {
        return stringServerPort;
    }

    public int getNumericServerPort() {
        return numericServerPort;
    }

    public int getCode() {
        return code;
    }

    public ServerConfiguration() {
    }
}
