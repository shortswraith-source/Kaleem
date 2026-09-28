package com.example.data.datasource

import com.example.data.model.DiagnosticChoice
import com.example.data.model.PracticeScenario

object PracticeScenariosDataSource {
    fun getScenarios(): List<PracticeScenario> = listOf(
        PracticeScenario(
            id = "sim_no_display",
            title = "PC Powers On, Fans Spin, But No Display",
            category = "PC Hardware",
            customerComplaint = "\"I pressed the power button this morning. The fans spin loudly and the case lights turn on, but my monitor says 'No Signal' and stays completely black!\"",
            deviceSpecs = "Custom Desktop: AMD Ryzen 5 5600X, MSI B550 Gaming Plus, 16GB DDR4 (2x8GB), Nvidia RTX 3060, 650W PSU.",
            availableSymptoms = listOf(
                "Monitor shows 'HDMI 1 No Signal' and enters sleep mode.",
                "Motherboard EZ Debug LED stays solidly illuminated on 'DRAM'.",
                "Keyboard Caps Lock LED does not respond or toggle.",
                "Case fans and GPU fans are spinning."
            ),
            possibleCauses = listOf(
                "Monitor HDMI cable plugged into motherboard I/O rather than GPU.",
                "RAM sticks unseated or oxidized gold contacts failing POST.",
                "Corrupted CMOS memory timing parameters.",
                "Dead graphics card or failing PCIe power cable."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Immediately buy a new $400 GPU and replace the graphics card.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Incorrect & Wasteful: Never replace expensive components before checking cables, diagnostic LEDs, and reseating RAM."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Inspect video cable connection, check motherboard DRAM Debug LED, and reseat RAM in Slot 2.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Excellent Choice! You check the cables, observe the DRAM LED, and reseat the memory module with a single stick in Slot 2 (A2)."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Reinstall Windows using a bootable USB drive.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Impossible: The computer is failing basic hardware POST before the motherboard even initiates the bootloader. Windows cannot run."
                ),
                DiagnosticChoice(
                    id = "c4",
                    text = "Reflash the motherboard BIOS firmware blind with a jumper.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "High Risk: Performing blind firmware flashes on unstable power or unverified RAM can permanently brick the motherboard."
                )
            ),
            correctChoiceId = "c2",
            systematicDiagnosticPath = "1) Check monitor input & verify HDMI/DP is connected to RTX 3060 (not motherboard). 2) Observe EZ Debug LEDs: 'DRAM' is lit. 3) Power off, disconnect AC power, remove both RAM sticks. 4) Clean gold fingers with pencil eraser. 5) Insert one stick into Slot A2 until side latches click. 6) Power on.",
            finalSolution = "Reseating a single RAM stick in Slot 2 allowed the memory controller to successfully train memory timings. System passed POST with single beep, video signal initialized, and both sticks were reinstalled in Slots 2 and 4 in dual-channel.",
            proTechnicianRule = "Over 70% of 'Fans spin, No display' symptoms are resolved by cleaning and reseating RAM or resetting CMOS.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_hdd_not_detected",
            title = "Secondary Internal HDD Not Showing in Windows",
            category = "Storage & Hard Drives",
            customerComplaint = "\"I bought a 2TB Seagate hard drive for storing games and photos. I plugged it in inside the PC case, but when I open 'This PC', only my C: drive is there!\"",
            deviceSpecs = "Dell OptiPlex Tower: Windows 11, 256GB NVMe SSD (C:), newly installed 2TB 3.5\" SATA HDD.",
            availableSymptoms = listOf(
                "Windows File Explorer shows only C: Local Disk.",
                "BIOS System Information lists 'SATA 1: ST2000DM008 2000GB'.",
                "Hard drive feels warm and vibrates smoothly with no clicking sounds."
            ),
            possibleCauses = listOf(
                "Brand new drive is unallocated and uninitialized in Windows.",
                "Drive is missing a drive letter assignment.",
                "Loose SATA data or power cable.",
                "Defective hard drive."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Open Windows Disk Management (diskmgmt.msc) to check disk initialization and partition status.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Perfect! BIOS sees the drive, so hardware is 100% fine. Disk Management is the exact tool to initialize new drives."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Return the drive to the store as defective hardware.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Incorrect: The drive is physically working and recognized by BIOS. Returning it wastes client time."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Run 'chkdsk C: /f /r' in Command Prompt.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Irrelevant: Checking drive C: will not do anything for an unpartitioned secondary drive."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) Check BIOS -> SATA-1 detects 2TB Seagate (hardware OK). 2) Boot Windows -> Win+X -> Disk Management. 3) Prompt appears: 'You must initialize a disk before Logical Disk Manager can access it'. 4) Choose GPT partition style. 5) Right-click Unallocated Space -> New Simple Volume -> NTFS -> Assign drive letter D:.",
            finalSolution = "New retail storage drives ship raw without partitions. Initializing the disk as GPT and formatting a New Simple Volume as NTFS assigned drive letter D:, making it instantly accessible in File Explorer.",
            proTechnicianRule = "Hardware visible in BIOS + missing in File Explorer = Uninitialized or unassigned drive letter in Disk Management.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_windows_wont_boot",
            title = "Windows Won't Boot: INACCESSIBLE_BOOT_DEVICE",
            category = "Windows OS",
            customerComplaint = "\"I went into BIOS to turn on virtualization for my work, and now Windows crashes on startup with a Blue Screen saying INACCESSIBLE_BOOT_DEVICE, then reboots in a loop!\"",
            deviceSpecs = "Lenovo Legion Laptop: Windows 11, Intel Core i7, 1TB NVMe SSD.",
            availableSymptoms = listOf(
                "Windows spinner circles for 3 seconds then flashes Blue Screen.",
                "Stop Code: INACCESSIBLE_BOOT_DEVICE.",
                "Automatic Repair fails with 'Your PC did not start correctly'.",
                "Customer admits changing BIOS settings right before crash."
            ),
            possibleCauses = listOf(
                "SATA / VMD Controller mode was accidentally toggled from AHCI/NVMe to Intel RST RAID (or vice-versa).",
                "Damaged EFI bootloader files.",
                "Corrupted registry SYSTEM hive.",
                "Failing SSD."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Format the SSD and clean install Windows from scratch, losing all customer files.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Terrible choice! Formatting causes catastrophic data loss when only a single BIOS controller setting was changed."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Enter BIOS Setup and check Storage / VMD / SATA controller configuration.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Spot on! The issue started right after the client modified BIOS settings. Checking storage controller mode is the logical first step."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Replace the NVMe SSD hardware.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Incorrect: The SSD is not damaged; the kernel driver simply cannot mount the boot volume due to storage controller mismatch."
                )
            ),
            correctChoiceId = "c2",
            systematicDiagnosticPath = "1) Enter BIOS (F2 / Fn+F2). 2) Navigate to Configuration / Storage. 3) Notice 'Intel VMD Controller' was set to Disabled. 4) Windows was originally installed with Intel RST VMD drivers. 5) Set Intel VMD Controller back to 'Enabled'. 6) Press F10 to Save & Exit. 7) Boot normally.",
            finalSolution = "Toggling the VMD/SATA controller back to its original state allowed Windows storage drivers to locate and mount the boot partition. Windows booted to desktop in 6 seconds with zero data loss.",
            proTechnicianRule = "Always ask 'What changed right before the failure occurred?' If BIOS settings changed, inspect storage controller modes first.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_printer_offline",
            title = "Office Network Printer Showing 'Offline' on All PCs",
            category = "Printers",
            customerComplaint = "\"None of the 6 computers in our accounting office can print to the shared HP LaserJet. Every computer says 'Printer Offline'.\"",
            deviceSpecs = "Network Office: HP LaserJet Pro M404dn connected via Ethernet to Netgear Gigabit switch.",
            availableSymptoms = listOf(
                "All 6 Windows workstations display 'Status: Offline'.",
                "Printer has a green ready light on its front display panel.",
                "Router rebooted last night following a power outage.",
                "Workstations configured using IP address 192.168.1.145."
            ),
            possibleCauses = listOf(
                "Printer was configured via DHCP without reservation; router reboot gave printer a new IP (192.168.1.189).",
                "Print Spooler service crashed on all 6 computers simultaneously (unlikely).",
                "Damaged Ethernet cable from printer to switch.",
                "Printer fuser error."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Print a Network Configuration page on the printer to check its current IP address.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Great diagnostic step! Verifying the printer's physical network settings reveals if its IP changed."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Uninstall printer drivers on all 6 computers and reinstall.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Time-wasting: Drivers rarely corrupt simultaneously across 6 separate computers."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Buy a new network switch.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Incorrect: Other computers on the switch have normal LAN access."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) On printer panel: Setup -> Reports -> Network Configuration. 2) Current IP shows 192.168.1.189 (was 192.168.1.145!). 3) Router DHCP assigned a new IP after reboot. 4) Solution: Set printer to STATIC IP 192.168.1.200 (outside DHCP pool). 5) Update Standard TCP/IP Port on workstations.",
            finalSolution = "Configured a permanent Static IP (192.168.1.200) and disabled SNMP status in port settings. Updated the port IP on workstations, restoring instant printing across all office PCs permanently.",
            proTechnicianRule = "Always assign STATIC IPs or DHCP MAC Reservations to network printers to prevent IP drift on router reboots.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_internet_down",
            title = "Connected with No Internet (Yellow Triangle)",
            category = "Networking",
            customerComplaint = "\"My Ethernet cable is plugged in, and Windows shows the network icon with a yellow exclamation mark saying 'Connected, no internet'. I can't open Google.\"",
            deviceSpecs = "Windows 11 Workstation, Realtek PCIe GbE Family Controller, TP-Link Archer Router.",
            availableSymptoms = listOf(
                "Windows shows 'No Internet Access'.",
                "CMD 'ipconfig' shows IPv4 Address: 169.254.120.45, Subnet: 255.255.0.0, Default Gateway: blank.",
                "Smartphone on Wi-Fi has working internet."
            ),
            possibleCauses = listOf(
                "PC failed to receive an IP from DHCP server (APIPA 169.254.x.x self-assignment).",
                "Damaged Ethernet cable or disconnected switch port.",
                "Corrupted Windows TCP/IP stack or Winsock catalog.",
                "Entire neighborhood ISP outage."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Call ISP and complain about a city-wide outage.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Wrong: The smartphone on Wi-Fi has working internet, proving the ISP connection is completely fine!"
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Inspect the IP address: 169.254.x.x is APIPA. Test Ethernet cable and run 'ipconfig /release' and 'ipconfig /renew'.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Spot on! Recognizing 169.254.x.x as APIPA points directly to a DHCP / physical layer communication breakdown."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Format Windows partition.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Drastic & Unnecessary: A network configuration glitch never warrants OS reinstallation."
                )
            ),
            correctChoiceId = "c2",
            systematicDiagnosticPath = "1) 169.254.x.x confirms PC did not receive DORA DHCP lease. 2) Inspect Ethernet cable: clip on RJ45 is broken and loose in wall plate. 3) Replace cable with known-good Cat6 patch cord. 4) Run 'ipconfig /renew'. 5) PC immediately receives 192.168.1.112 with Gateway 192.168.1.1.",
            finalSolution = "The broken RJ45 connector clip caused loose contact on pin pairs 1 and 2, dropping DHCP handshake packets. Replacing the patch cable and renewing DHCP restored full gigabit internet.",
            proTechnicianRule = "Any 169.254.x.x IP address means DHCP request timed out. Always check physical cable and DHCP server availability.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_dvr_hdd_missing",
            title = "Security DVR Beeping: HDD Not Detected",
            category = "CCTV & DVR",
            customerComplaint = "\"Our store CCTV recorder started beeping loudly every 10 seconds. The screen shows 'No HDD Found' and none of our cameras are recording!\"",
            deviceSpecs = "Hikvision 8-Channel DVR with 4TB Western Digital Purple Surveillance HDD.",
            availableSymptoms = listOf(
                "Loud continuous buzzer beeps from DVR.",
                "Storage Management status: 'No Disk'.",
                "DVR power supply is a small 12V 1.5A brick plugged into a power strip.",
                "Hard drive does not vibrate or feel warm to the touch."
            ),
            possibleCauses = listOf(
                "Degraded 12V power adapter unable to supply 12V spin-up current to 3.5\" HDD.",
                "Loose or broken internal SATA power / data cable.",
                "Defective hard drive motor.",
                "Failed DVR motherboard controller."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Measure DVR power adapter voltage with multimeter and test with a regulated 12V 3A power brick.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Expert IT Insight! Under-rated or degraded 12V power bricks are responsible for the vast majority of DVR HDD detection failures."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Cut the buzzer wire with pliers.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Dangerous: Muting the buzzer ignores the root cause—the store is recording zero security video!"
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Factory reset DVR firmware.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Unhelpful: If the drive is not receiving physical power to spin, software resets will do nothing."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) Measure power brick with multimeter: Output reads 10.4V under load (should be 12.0V). 2) Disconnect weak 1.5A adapter. 3) Connect a brand new 12V 3.0A power supply. 4) Power on DVR. 5) HDD spins up with audible head calibration. 6) Storage menu displays '4TB WD Purple - Status: Normal'.",
            finalSolution = "The 12V 1.5A power adapter had degraded internal filtering capacitors, dropping voltage below the minimum 11.4V threshold required for the HDD spindle motor to spin. Upgrading to a 12V 3A adapter restored 100% operational recording.",
            proTechnicianRule = "Always test power adapter amperage when DVRs lose hard drive detection before replacing the drive.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_pc_overheating",
            title = "PC Shuts Down Abruptly After 5 Minutes of Gaming",
            category = "PC Hardware",
            customerComplaint = "\"I turn on my PC, it boots fine into Windows. But as soon as I launch a game or video editor, the computer shuts down with zero warning after 5 minutes!\"",
            deviceSpecs = "Gaming Desktop: Intel Core i7-13700K, 240mm AIO Liquid Cooler, RTX 4070, 750W PSU.",
            availableSymptoms = listOf(
                "System boots normally, runs desktop apps smoothly.",
                "Under Cinebench load, CPU temperature hits 100°C within 30 seconds.",
                "CPU thermal throttles clock speed down from 5.3 GHz to 1.8 GHz before sudden thermal safety shutdown.",
                "One AIO cooler tube is scorching hot while the radiator and second tube remain stone cold."
            ),
            possibleCauses = listOf(
                "AIO liquid cooler pump is dead or impeller is stuck.",
                "Dried, crusty thermal paste or plastic peel left on cooler base.",
                "Dust choked radiator fins.",
                "Defective power supply."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Verify AIO pump RPM in BIOS / HWMonitor and feel coolant tubes for pump vibration.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Exact right call! A temperature hitting 100°C in seconds with one hot tube and cold radiator points directly to zero coolant circulation."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Replace the graphics card.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Irrelevant: The telemetry data clearly demonstrates CPU thermal trip at 100°C."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Turn off thermal protection in BIOS so the PC stops shutting down.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Catastrophic! Disabling thermal safety limits will literally melt silicon and permanently destroy the CPU."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) HWMonitor shows AIO_PUMP header reads 0 RPM. 2) Touching pump block reveals no fluid movement or vibration. 3) Pump motor has seized. 4) Replace dead AIO with a dual-tower air cooler (Peerless Assassin 120). 5) Clean CPU with 99% alcohol and re-paste. 6) Retest under load: Max temp 78°C.",
            finalSolution = "Diagnosed dead AIO liquid cooling pump. Replacing the seized cooler and applying fresh thermal compound lowered load temperatures from 100°C to a stable 78°C under full load.",
            proTechnicianRule = "One hot hose + cold radiator + 100°C CPU = Failed AIO liquid pump.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_usb_not_detected",
            title = "USB Flash Drive Inserted But Not Recognized",
            category = "Software & Tools",
            customerComplaint = "\"I plugged my USB flash drive into the front of my computer, and Windows made a sound, but nothing pops up and it doesn't show up in File Explorer.\"",
            deviceSpecs = "Windows 10 Desktop, 64GB SanDisk Ultra USB 3.0 Flash Drive.",
            availableSymptoms = listOf(
                "Windows chime plays upon insertion.",
                "File Explorer 'This PC' does not show the drive.",
                "Device Manager shows 'SanDisk Ultra USB Device' under Disk drives.",
                "Disk Management shows 'Disk 1 Removable 57.8GB' with no drive letter assigned."
            ),
            possibleCauses = listOf(
                "Drive is missing an assigned drive letter in Windows.",
                "Corrupted RAW filesystem.",
                "Underpowered front USB port.",
                "Hardware controller failure on flash drive."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Open Disk Management, right-click the healthy volume, and assign a Drive Letter (e.g. E:).",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Spot on! The drive is healthy and recognized, but lacks an assigned mount point letter."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Right-click and format the flash drive immediately.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Unacceptable: Formatting will permanently erase all customer documents on the USB drive!"
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Throw the flash drive away.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Premature: The storage volume is completely healthy."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) In Device Manager -> Device is detected healthy. 2) In Disk Management -> Volume is formatted NTFS, healthy, but has no letter. 3) Right-click partition -> 'Change Drive Letter and Paths...'. 4) Click 'Add' -> Assign letter E: -> Click OK. 5) Drive opens immediately in File Explorer.",
            finalSolution = "Windows automount service had failed to assign a free drive letter. Assigning drive letter E: in Disk Management mounted the drive instantly with all customer data intact.",
            proTechnicianRule = "Always check Disk Management for missing drive letters before declaring a USB drive corrupted or unreadable.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_no_sound",
            title = "No Audio from Desktop Speakers After Windows Update",
            category = "Windows OS",
            customerComplaint = "\"Windows installed updates last night. Today when I watch videos or play music, there is zero sound coming out of my speakers!\"",
            deviceSpecs = "Desktop PC, Windows 11, Realtek Audio Codec, External 3.5mm Stereo Speakers.",
            availableSymptoms = listOf(
                "Windows volume slider is at 100% and audio meter bounces when playing YouTube.",
                "No sound heard from speakers.",
                "Speakers power LED is green.",
                "Monitor has built-in speakers over HDMI."
            ),
            possibleCauses = listOf(
                "Windows update switched Default Audio Output Device to Monitor HDMI instead of Realtek Audio 3.5mm.",
                "Speaker cable plugged into blue Line-In instead of green Line-Out.",
                "Speaker volume knob turned all the way down.",
                "Corrupted Realtek high definition audio driver."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Check Windows Sound Output device selection (Sound Flyout / mmsys.cpl).",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Bingo! The visual audio meter bouncing proves audio is playing, but being directed to the wrong output channel."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Roll back entire Windows operating system to previous build.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Overkill: Never roll back an entire OS for a simple audio output endpoint setting."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Purchase a new sound card.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Unnecessary: The internal sound hardware is functioning normally."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) Click speaker icon on taskbar. 2) Click audio output selector arrow next to volume slider. 3) Currently selected: 'Samsung Monitor (Nvidia High Definition Audio)'. 4) Switch selection to 'Speakers (Realtek High Definition Audio)'. 5) Sound plays crystal clear.",
            finalSolution = "Windows Update had re-prioritized the HDMI monitor audio device over the motherboard 3.5mm analog output. Selecting 'Speakers (Realtek)' as the default audio device restored sound immediately.",
            proTechnicianRule = "Bouncing volume meter + no sound = Audio is playing to the wrong playback device.",
            isDataHazard = false
        ),
        PracticeScenario(
            id = "sim_driver_missing",
            title = "Yellow Exclamation Mark on Network Adapter (Driver Missing)",
            category = "Software & Tools",
            customerComplaint = "\"I just formatted my custom gaming PC with a clean Windows install, but there is no Wi-Fi or Ethernet connection at all! I can't even get online to download drivers.\"",
            deviceSpecs = "Custom PC: Asus ROG Strix Z790-E Motherboard, Intel I226-V 2.5Gb Ethernet, Wi-Fi 6E.",
            availableSymptoms = listOf(
                "Network icon in taskbar shows a globe with a 'no entry' symbol.",
                "Device Manager shows yellow exclamation marks under 'Other devices': Ethernet Controller, Network Controller.",
                "Hardware IDs show: VEN_8086&DEV_125C (Intel I226-V GbE).",
                "Computer has no secondary internet access."
            ),
            possibleCauses = listOf(
                "Windows installation ISO lacks native inbox drivers for high-end 2.5Gb Intel NIC and Wi-Fi 6E chipset.",
                "Defective motherboard network chip.",
                "Disabled in BIOS.",
                "Missing router connection."
            ),
            diagnosticChoices = listOf(
                DiagnosticChoice(
                    id = "c1",
                    text = "Use a secondary computer or smartphone to download the Intel LAN driver from Asus support site onto a USB flash drive.",
                    isCorrectFirstStep = true,
                    outcomeFeedback = "Outstanding! The professional technician protocol: download OEM drivers on another device and bridge via USB."
                ),
                DiagnosticChoice(
                    id = "c2",
                    text = "Keep restarting the PC hoping Windows will magically find the driver offline.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Futile: An offline Windows installation cannot download drivers without network access."
                ),
                DiagnosticChoice(
                    id = "c3",
                    text = "Replace the motherboard.",
                    isCorrectFirstStep = false,
                    outcomeFeedback = "Incorrect: Brand new high-speed NICs routinely require manufacturer INF drivers."
                )
            ),
            correctChoiceId = "c1",
            systematicDiagnosticPath = "1) Connect smartphone to home Wi-Fi. 2) Download Asus Z790 Intel LAN driver zip file. 3) Transfer zip file to PC via USB cable (or flash drive). 4) Extract zip on PC. 5) Run AsusSetup.exe. 6) Realtek/Intel Ethernet initializes within 5 seconds. 7) Windows Update then auto-fetches all remaining drivers.",
            finalSolution = "Modern 2.5Gbps Intel and Realtek NICs often lack inbox drivers in standard Windows ISOs. Sourcing the official driver package on an alternate device and transferring via USB initialized the network adapter and restored full internet access.",
            proTechnicianRule = "Always keep a USB flash drive stocked with common Intel and Realtek LAN/Wi-Fi drivers in your technician toolkit.",
            isDataHazard = false
        )
    )
}
