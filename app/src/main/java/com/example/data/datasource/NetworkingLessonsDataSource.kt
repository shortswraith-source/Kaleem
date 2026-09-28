package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.Lesson
import com.example.data.model.LessonStep
import com.example.data.model.LessonWarning
import com.example.data.model.TroubleshootingItem

object NetworkingLessonsDataSource {
    fun getLessons(): List<Lesson> = listOf(
        Lesson(
            id = "net_lan",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Local Area Network (LAN) & Topologies",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "A Local Area Network (LAN) connects computers, printers, and servers within a limited geographical area like a home or office. Modern LANs use Star Topology powered by Layer 2 switches.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Star Topology & The Central Switch",
                    description = "Every device connects back to a central network switch via individual Ethernet cables. If one cable fails, only that single device drops; the rest of the LAN remains functional."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Hubs vs Switches",
                    description = "Hubs (obsolete) broadcast incoming frames out of every port, causing collisions. Switches learn device MAC addresses and forward frames ONLY to the destination port, operating in full duplex."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Collision vs Broadcast Domains",
                    description = "Each switch port represents its own independent Collision Domain. All ports on a standard switch share a single Broadcast Domain (divided only by VLANs or Routers)."
                )
            ),
            requiredTools = listOf("Gigabit Ethernet Switch", "Patch Cables"),
            importantWarnings = listOf(
                LessonWarning("Never plug both ends of an Ethernet patch cable into the same unmanaged switch; this creates a catastrophic switching broadcast storm that freezes the entire LAN within seconds!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Using old 10/100 Mbps Fast Ethernet switches on modern gigabit fiber internet connections, bottlenecking speeds to 94 Mbps."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Switch port LED flashes amber instead of green.",
                    fix = "Port has negotiated at 100 Mbps instead of 1000 Mbps Gigabit, indicating damaged cable pin pair 4/5 or 7/8."
                )
            ),
            iconKey = "network"
        ),
        Lesson(
            id = "net_ethernet",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Ethernet Cabling (Cat5e, Cat6, Cat6a & UTP/STP)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Twisted pair Ethernet cabling cancels electromagnetic interference (EMI). Choosing the right category ensures full gigabit and 10-gigabit throughput over distance.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Cable Categories & Bandwidth",
                    description = "Cat5e: 1 Gbps up to 100m (100MHz) | Cat6: 1 Gbps up to 100m, 10 Gbps up to 55m (250MHz) | Cat6a: 10 Gbps up to full 100m (500MHz, internal spline divider) | Cat7/Cat8: Specialized data centers."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "UTP vs STP Shielding",
                    description = "UTP (Unshielded Twisted Pair): Standard for residential/office runs. STP / FTP (Shielded): Foil wrapped around pairs, required when running near elevator motors, industrial machinery, or fluorescent ballasts."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "The 100-Meter Distance Limit",
                    description = "Standard Ethernet maximum run length is strictly 100 meters (90m solid core horizontal structured run + 10m stranded patch cords). Exceeding this introduces packet jitter and signal loss."
                )
            ),
            requiredTools = listOf("Cat6 Solid Copper Cable Spool", "Cable Stripper"),
            importantWarnings = listOf(
                LessonWarning("Beware of cheap CCA (Copper Clad Aluminum) cables. CCA cables break easily, overheat dangerously under PoE loads, and violate IEEE 802.3 specifications. Always specify 100% Pure Bare Copper!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Running unshielded Ethernet cables directly draped across high-voltage AC electrical conduit in drop ceilings."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Gigabit NIC refuses to link above 100 Mbps on a Cat6 cable.",
                    fix = "Gigabit requires all 8 wires (4 pairs). Fast Ethernet requires only 4 wires (pairs 2 and 3). If any wire in pairs 1 or 4 is severed, the link negotiates at 100 Mbps."
                )
            ),
            iconKey = "cable"
        ),
        Lesson(
            id = "net_rj45",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "RJ45 Crimping & T568B Color Standard",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 15,
            explanation = "Terminating 8P8C (RJ45) modular connectors using the worldwide T568B pinout is one of the most fundamental skills of any IT technician.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "The Universal T568B Pinout Order",
                    description = "Hold connector with clip facing AWAY/DOWN. Order pins 1 to 8 from LEFT to RIGHT: 1: White-Orange, 2: Orange, 3: White-Green, 4: Blue, 5: White-Blue, 6: Green, 7: White-Brown, 8: Brown.",
                    proTip = "Mnemonic: 'Sun rises (Orange), Grass grows (Green split), Blue sky/water in middle, Earth/soil at the end (Brown)'."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Preparation & Untwisting",
                    description = "Strip 1.5 inches of outer jacket. Untwist pairs. Straighten wires flat with thumb and index finger. Cut wires flush with diagonal cutters at exactly 1/2 inch length."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Crimp & Verify Cable Jacket Anchor",
                    description = "Push wires firmly into RJ45 plug until all copper tips touch the very front. The outer cable jacket MUST enter inside the connector past the plastic crimp anchor. Crimp firmly with ratcheting tool."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Testing with Continuity Cable Tester",
                    description = "Plug both ends into an RJ45 Master and Remote tester. Lights 1 through 8 must sequence in identical 1-to-8 order on both units."
                )
            ),
            requiredTools = listOf("Ratcheting RJ45 Crimper (Pass-through recommended)", "RJ45 Connectors", "Wire Stripper", "Cable Tester"),
            importantWarnings = listOf(
                LessonWarning("Never leave exposed twisted pairs outside the RJ45 shell; untwisted length exceeding 1/2 inch destroys NEXT (Near-End Crosstalk) shielding.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Swapping Pin 4 (Blue) and Pin 5 (White-Blue), or mixing T568A on one end and T568B on the other without intending to build a crossover cable."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Cable tester skips LED #3 and #6.",
                    fix = "Split pair error. Pin 3 (White-Green) or Pin 6 (Green) is not making contact with the gold piercing tooth. Cut off plug and recrimp."
                )
            ),
            iconKey = "tool"
        ),
        Lesson(
            id = "net_ip_address",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "IPv4 Addresses, Classes & Subnet Masks (/24)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 16,
            explanation = "An IP address is a 32-bit logical identifier that allows devices to locate each other across networks. Understanding private RFC 1918 ranges and subnet masks enables proper network segmentation.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "IPv4 Octet Structure",
                    description = "Composed of four 8-bit octets (0-255) separated by dots, totaling 32 bits (e.g., 192.168.1.50)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Private RFC 1918 IP Ranges (Non-Routable on Public Internet)",
                    description = "Class A: 10.0.0.0 to 10.255.255.255 (/8) | Class B: 172.16.0.0 to 172.31.255.255 (/12) | Class C: 192.168.0.0 to 192.168.255.255 (/16)."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "The Subnet Mask (/24)",
                    description = "255.255.255.0 (/24) indicates the first 24 bits represent the Network ID and the last 8 bits represent Host IDs (allowing 254 usable devices: .1 to .254).",
                    proTip = ".0 is reserved for Network ID and .255 is reserved for Broadcast."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "APIPA / Link-Local (169.254.x.x)",
                    description = "If Windows cannot reach a DHCP server, it self-assigns an Automatic Private IP Address (APIPA) starting with 169.254. This indicates DHCP failure!"
                )
            ),
            requiredTools = listOf("Command Prompt (ipconfig)", "Subnet Calculator"),
            importantWarnings = listOf(
                LessonWarning("Two devices on the same local subnet cannot have the same IP address; IP conflicts disconnect both devices intermittently.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Assuming a computer with an IP of 169.254.85.12 has working internet connectivity."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Two PCs cannot ping each other on the same physical switch.",
                    fix = "Check subnet masks and IP octets. If PC A is 192.168.1.10/24 and PC B is 192.168.0.10/24, they reside on different logical subnets and cannot communicate without a router."
                )
            ),
            iconKey = "tag"
        ),
        Lesson(
            id = "net_dhcp",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "DHCP (DORA Process & Lease Time)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Dynamic Host Configuration Protocol (DHCP) automatically assigns IP addresses, subnet masks, default gateways, and DNS servers to client workstations upon network connection.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "The 4-Step DORA Handshake",
                    description = "1) Discover (Client broadcasts: 'Is there a DHCP server?'), 2) Offer (Server offers IP: 'You can use 192.168.1.45'), 3) Request (Client replies: 'I accept 192.168.1.45'), 4) Acknowledge (Server commits lease to database).",
                    proTip = "DORA handshake operates over UDP ports 67 (server) and 68 (client)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "DHCP Scope & Lease Duration",
                    description = "The Scope defines the pool of allocatable IPs (e.g., 192.168.1.100 - .200). Lease time defines how long client holds IP (typically 24 hours in office, 2 hours in guest coffee shop)."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Renewing Lease via CMD",
                    description = "ipconfig /release (drops current lease) followed by ipconfig /renew (triggers fresh DORA cycle).",
                    commandOrKey = "ipconfig /release && ipconfig /renew"
                )
            ),
            requiredTools = listOf("Command Prompt", "Router Admin Interface"),
            importantWarnings = listOf(
                LessonWarning("Rogue DHCP Server Warning: Connecting an unconfigured home Wi-Fi router into an office wall jack will cause it to hand out incorrect 192.168.0.x gateway IPs, disabling office internet!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Configuring static IPs on printers inside the middle of the router's active DHCP assignable pool."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Workstation takes 60 seconds to connect and gets 169.254.x.x IP.",
                    fix = "DHCP server is unreachable or pool is exhausted. Check router DHCP status or restart router."
                )
            ),
            iconKey = "sync"
        ),
        Lesson(
            id = "net_dns",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "DNS (Domain Name System & Flush DNS)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "DNS is the internet's phonebook, resolving human-readable domain names (like google.com) into machine-readable IP addresses (like 142.250.190.46).",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "DNS Hierarchy & Resolution",
                    description = "Root Servers (.) -> Top-Level Domain (.com/.net) -> Authoritative Nameservers. Local PCs first check local HOSTS file and DNS resolver cache."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Public DNS Providers",
                    description = "Cloudflare (1.1.1.1 / 1.0.0.1) - Fastest privacy-focused | Google (8.8.8.8 / 8.8.4.4) - Universal reliability | Quad9 (9.9.9.9) - Malware blocking."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Flushing Corrupted DNS Cache in Windows",
                    description = "Open CMD as Admin and type: ipconfig /flushdns. Windows confirms 'Successfully flushed the DNS Resolver Cache'.",
                    commandOrKey = "ipconfig /flushdns"
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Testing Resolution with Nslookup",
                    description = "Type 'nslookup google.com' or 'nslookup google.com 8.8.8.8' to query a specific server directly.",
                    commandOrKey = "nslookup google.com"
                )
            ),
            requiredTools = listOf("Elevated Command Prompt", "Network Adapter Properties"),
            importantWarnings = listOf(
                LessonWarning("Malware frequently edits C:\\Windows\\System32\\drivers\\etc\\hosts to hijack bank URLs and redirect users to phishing sites. Always check this file during security cleanups.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving ISP default DNS servers active when ISP DNS outages cause internet to appear down even though IP routing is functional."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Can ping 8.8.8.8 successfully, but cannot open any website in browser.",
                    fix = "100% textbook DNS failure. Your IP routing works, but domain name lookup is dead. Change DNS in IPv4 properties to 8.8.8.8 and 1.1.1.1."
                )
            ),
            iconKey = "globe"
        ),
        Lesson(
            id = "net_gateway",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Default Gateway & Routing Table",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 10,
            explanation = "The Default Gateway is the internal IP address of the local router. Any network packet destined for an IP address outside the local subnet is forwarded to the gateway.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Finding the Gateway IP",
                    description = "Open CMD and type 'ipconfig'. Look for the line 'Default Gateway . . . . . . . . . : 192.168.1.1'.",
                    commandOrKey = "ipconfig"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Role of Network Address Translation (NAT)",
                    description = "The router replaces the client private IP (e.g. 192.168.1.50) with the ISP public WAN IP on outgoing packets, mapping source port numbers in a state table."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Viewing the Windows Routing Table",
                    description = "Type 'route print' in CMD. Destination 0.0.0.0 with Netmask 0.0.0.0 points to the Default Gateway.",
                    commandOrKey = "route print"
                )
            ),
            requiredTools = listOf("Command Prompt"),
            importantWarnings = listOf(
                LessonWarning("Never configure two default gateways on two different active network adapters (e.g. Wi-Fi and Ethernet) on a single Windows PC; it leads to gateway confusion and dropped packets.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Manually setting static IP on a PC and forgetting to fill in the Default Gateway field."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Cannot ping the Default Gateway (192.168.1.1).",
                    fix = "Physical cable unplugged, bad Wi-Fi connection, or router is completely frozen. Power-cycle the router."
                )
            ),
            iconKey = "router"
        ),
        Lesson(
            id = "net_router",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Router Setup & Wi-Fi Security Hardening",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 16,
            explanation = "Configuring a new wireless router involves setting WAN connection types, securing administrator credentials, establishing SSIDs, and configuring WPA3/WPA2 encryption.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Accessing Web Admin Interface",
                    description = "Connect PC to router LAN port. Open browser and enter default gateway IP (usually 192.168.1.1, 192.168.0.1, or 10.0.0.1)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Change Default Admin Password",
                    description = "Never leave manufacturer defaults ('admin/admin' or 'admin/password'). Set a strong 16-character alphanumeric passphrase."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Wireless Security Settings",
                    description = "Select 'WPA2-PSK (AES)' or 'WPA3-Personal'. Never select WEP or TKIP (both broken and insecure). Assign separate SSIDs for 2.4GHz and 5GHz bands.",
                    proTip = "Smart home IoT devices often require 2.4GHz only."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Disable WPS & Remote WAN Management",
                    description = "Disable Wi-Fi Protected Setup (WPS) PIN, which is vulnerable to brute-force Reaver attacks. Ensure Remote Management over WAN is disabled."
                )
            ),
            requiredTools = listOf("Ethernet Cable", "Web Browser", "Router Label Admin Credentials"),
            importantWarnings = listOf(
                LessonWarning("Never click 'Factory Reset' button unless customer agrees to re-enter their ISP PPPoE credentials and Wi-Fi network settings from scratch!", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Leaving WPS enabled, allowing anyone to bypass the Wi-Fi password in seconds using PIN algorithms."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Client forgot custom router admin password.",
                    fix = "Hold rear reset button with paperclip for 15 seconds to restore factory defaults, then reconfigure using credentials printed on bottom sticker."
                )
            ),
            iconKey = "wifi"
        ),
        Lesson(
            id = "net_wifi_troubleshoot",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Wi-Fi Troubleshooting (2.4GHz vs 5GHz Channels)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Resolving intermittent Wi-Fi drops, dead zones, and slow wireless speeds by analyzing frequency spectrums, signal attenuation, and co-channel interference.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "2.4 GHz vs 5 GHz Spectrum Characteristics",
                    description = "2.4 GHz: Superior range, passes through walls easily, but crowded and limited to ~75-100 Mbps. 5 GHz: Shorter range, poor wall penetration, but high speed (600-1200+ Mbps) with 24 non-overlapping channels."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "The 3 Non-Overlapping 2.4GHz Channels",
                    description = "In 2.4GHz, only channels 1, 6, and 11 do not overlap. Setting a router to channel 3 or 8 causes severe adjacent channel interference with neighboring routers!"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Signal Strength (RSSI) Benchmarks",
                    description = "-30 to -50 dBm: Excellent | -60 to -67 dBm: Minimum for smooth VoIP/video calls | -75 dBm: Weak, packet loss begins | -85 dBm: Disconnecting."
                )
            ),
            requiredTools = listOf("Wi-Fi Analyzer App", "Laptop / Smartphone"),
            importantWarnings = listOf(
                LessonWarning("Microwave ovens, baby monitors, and older cordless phones operate on the 2.4GHz ISM band and will knock out 2.4GHz Wi-Fi when running.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Placing the Wi-Fi router inside a closed metal server cabinet or behind a large metal refrigerator."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Wi-Fi speed drops from 300 Mbps to 10 Mbps in the evening.",
                    fix = "Neighbor router interference. Scan channels with Wi-Fi Analyzer and switch from crowded Auto/Channel 6 to clean Channel 1 or 11."
                )
            ),
            iconKey = "wifi"
        ),
        Lesson(
            id = "net_ping_traceroute",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Ping, Tracert & Packet Loss Analysis",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "ICMP diagnostic utilities (ping and tracert) allow technicians to systematically test each network hop from local PC to destination cloud servers.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "The 4-Step Ping Ladder Rule",
                    description = "1) ping 127.0.0.1 (Tests internal TCP/IP stack) -> 2) ping <Local_PC_IP> (Tests physical NIC driver) -> 3) ping <Gateway_IP> (Tests LAN connection to router) -> 4) ping 8.8.8.8 (Tests ISP WAN routing)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Continuous Ping Testing",
                    description = "Type 'ping 1.1.1.1 -t'. Watch latency (ms). Press Ctrl + C to stop and review Packet Loss percentage.",
                    commandOrKey = "ping 1.1.1.1 -t"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Hop Isolation with Tracert",
                    description = "Type 'tracert google.com'. Tracert shows each router hop along the path. If latency spikes from 15ms to 300ms at Hop 3, the ISP node is congested.",
                    commandOrKey = "tracert google.com"
                )
            ),
            requiredTools = listOf("Command Prompt / PowerShell"),
            importantWarnings = listOf(
                LessonWarning("Some enterprise firewalls block ICMP Echo Requests by default. A 'Request Timed Out' does not always mean the server is completely down.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Assuming high ping spikes in online games are game server faults when local Wi-Fi packet loss is at 12%."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Ping returns 'Destination Host Unreachable'.",
                    fix = "Local PC has no route to the destination. Check IP address, subnet mask, and make sure default gateway is configured."
                )
            ),
            iconKey = "terminal"
        ),
        Lesson(
            id = "net_cmd_commands",
            categoryId = "networking",
            categoryTitle = "Networking",
            title = "Essential Networking CLI Commands (ipconfig, netstat, arp)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "Mastering core Windows networking command-line utilities provides instant diagnostic feedback without navigating nested GUI setting screens.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "ipconfig /all",
                    description = "Displays complete network adapter details: Physical MAC Address, DHCP Server IP, Lease Obtainment date, and DNS server addresses.",
                    commandOrKey = "ipconfig /all"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "netstat -ano",
                    description = "Lists all active TCP and UDP listening ports and external connections along with their owning Process ID (PID). Essential for discovering malware beacons.",
                    commandOrKey = "netstat -ano"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "arp -a",
                    description = "Displays the local Address Resolution Protocol table, mapping local IP addresses to physical MAC hardware addresses.",
                    commandOrKey = "arp -a"
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "PowerShell Test-NetConnection",
                    description = "Type 'Test-NetConnection 192.168.1.200 -Port 80' to verify if a remote web server or printer port is open through the firewall.",
                    commandOrKey = "Test-NetConnection <IP> -Port <Port>"
                )
            ),
            requiredTools = listOf("Elevated Command Prompt / PowerShell"),
            importantWarnings = listOf(
                LessonWarning("Running 'netsh int ip reset' and 'netsh winsock reset' will wipe all custom static IP configurations and VPN adapter bindings.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Looking for open listening ports using standard task manager instead of netstat -ano."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Port 80 or 443 is blocked on local web server.",
                    fix = "Run 'netstat -ano | findstr :80' to find the PID occupying the port. Open Task Manager to identify the conflicting service (often IIS or Skype)."
                )
            ),
            iconKey = "code"
        )
    )
}
