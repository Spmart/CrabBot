package org.spmart.tphcrabbot.util;

public class DNSResponse {

    private String domain;
    private String dnsServer;
    private String ip;
    private String ptr;

    public DNSResponse(String domain, String dnsServer, String ip, String ptr) {
        this.domain = domain;
        this.dnsServer = dnsServer;
        this.ip = ip;
        this.ptr = ptr;
    }

    public DNSResponse(String domain, String dnsServer, String ip) {
        this.domain = domain;
        this.dnsServer = dnsServer;
        this.ip = ip;
        ptr = "";
    }

    public DNSResponse(String domain) {
        this.domain = domain;
        dnsServer = "Probably, it's an error!";
        ip = "Probably, it's an error!";
        ptr = "Probably, it's an error!";
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDnsServer() {
        return dnsServer;
    }

    public void setDnsServer(String dnsServer) {
        this.dnsServer = dnsServer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPtr() {
        return ptr;
    }

    public void setPtr(String ptr) {
        this.ptr = ptr;
    }
}
