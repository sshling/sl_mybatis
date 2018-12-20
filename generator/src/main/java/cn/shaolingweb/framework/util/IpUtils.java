package cn.shaolingweb.framework.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IpUtils {

	private static final String ipReg = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

	private static String serverIP = null;

	public boolean inBlock(String ip, IPBlock ipBlock) {
		return true;

	}
	public static String getIP(String prefix ) {

		if (serverIP == null) {
			try {
				Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
				InetAddress ip = null;
				while (allNetInterfaces.hasMoreElements()) {
					NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
					if (netInterface.isLoopback()) {
						continue;
					}
					Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
					while (addresses.hasMoreElements()) {
						ip = (InetAddress) addresses.nextElement();
						if (ip != null && ip instanceof Inet4Address) {
							serverIP = ip.getHostAddress().toString();
							if (!serverIP.startsWith(prefix)) {
								serverIP=null;
								continue;
							}
							
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return serverIP;
	}

	/**
	 * ip 点分十进制
	 *
	 * @param ip
	 * @return
	 */
	public static int[] divideToSegment(String ip) {

		if (!ip.matches(ipReg)) {
			return null;
		}

		String[] segments = ip.split("\\.");
		int int_segments[] = new int[segments.length];
		for (int i = 0; i < segments.length; i++) {
			int_segments[i] = Integer.parseInt(segments[i]);
		}

		return int_segments;
	}

	public static boolean inLAN(String ip) {
		IPBlock classA = new IPBlock("10.0.0.0", "255.0.0.0");
		IPBlock classC = new IPBlock("192.168.0.0", "255.255.0.0");
		ip = ip.trim();
		int[] netIdSegment = divideToSegment(ip);
		if (netIdSegment == null) {
			return false;
		}
		if (classA.inMyBlock(netIdSegment)) {
			return true;
		}
		if (classC.inMyBlock(netIdSegment)) {
			return true;
		}
		if (netIdSegment[0] == 172 && netIdSegment[1] >= 16 && netIdSegment[1] <= 31) {
			return true;
		}
		if ("127.0.0.1".equals(ip)) {
			return true;
		}
		return false;
	}

}
class IPBlock {

	private String subnetMask;
	private String netId;

	private int[] subnetMaskSegment;
	private int[] netIdSegment;

	public IPBlock(String netId, String subnetMask) {

		this.netId = netId;
		this.subnetMask = subnetMask;

		initSubnetMaskSegment(subnetMask);
		intiNetIdSegment(netId);
	}

	private void initSubnetMaskSegment(String ip) {
		this.subnetMaskSegment = IpUtils.divideToSegment(ip);
	}

	private void intiNetIdSegment(String ip) {
		this.netIdSegment = IpUtils.divideToSegment(ip);
	}

	public String getSubnetMask() {
		return subnetMask;
	}

	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}

	public String getNetId() {
		return netId;
	}

	public int[] getSubnetMaskSegment() {
		return subnetMaskSegment;
	}

	public int[] getNetIdSegment() {
		return netIdSegment;
	}

	public boolean inMyBlock(String ip) {

		int segments[] = IpUtils.divideToSegment(ip);
		if (segments == null || segments.length < 4) {
			return false;
		}

		for (int i = 0; i < subnetMaskSegment.length; i++) {
			if ((segments[i] & subnetMaskSegment[i]) != netIdSegment[i]) {
				return false;
			}
		}

		return true;
	}

	public boolean inMyBlock(int[] segments) {

		if (segments == null || segments.length < 4) {
			return false;
		}

		for (int i = 0; i < subnetMaskSegment.length; i++) {
			if ((segments[i] & subnetMaskSegment[i]) != netIdSegment[i]) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(123 & 255);
	}

}