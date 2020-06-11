package org.spmart.tphcrabbot.util;

/**
 * DNS response data object.
 */
public class DNSResponse {

    private String domain;
    private String dnsServer;
    private String ip;
    private String ptr;

    /**
     * Create DNSResponse object.
     * @param domain Domain name.
     * @param dnsServer DNS server hostname.
     * @param ip IP address.
     * @param ptr PTR (reverse DNS) record.
     */
    public DNSResponse(String domain, String dnsServer, String ip, String ptr) {
        this.domain = domain;
        this.dnsServer = dnsServer;
        this.ip = ip;
        this.ptr = ptr;
    }

    /**
     * Create DNSResponse object.
     * This constructor should be used if PTR not found.
     * @param domain Domain name.
     * @param dnsServer DNS server hostname.
     * @param ip IP address.
     */
    public DNSResponse(String domain, String dnsServer, String ip) {
        this.domain = domain;
        this.dnsServer = dnsServer;
        this.ip = ip;
        ptr = "";
    }

    /**
     * Create DNSResponse object.
     * This constructor should be used if you want to send an empty response.
     * @param domain Domain name.
     */
    public DNSResponse(String domain) {
        this.domain = domain;
        dnsServer = "Empty response";
        ip = "";
        ptr = "";
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
