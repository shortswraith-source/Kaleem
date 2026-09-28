package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.Lesson
import com.example.data.model.LessonStep
import com.example.data.model.LessonWarning
import com.example.data.model.TroubleshootingItem

object PrinterLessonsDataSource {
    fun getLessons(): List<Lesson> = listOf(
        Lesson(
            id = "print_install",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Printer Installation Fundamentals",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Printers connect via direct USB, wired Ethernet, or Wi-Fi. Understanding how the Windows Print Spooler renders print jobs and communicates with printer hardware is essential for IT field technicians.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Unpacking & Transit Locks",
                    description = "Remove all orange transit shipping tape, plastic clips, and cardboard inserts from internal roller paths. Shake toner/ink cartridge gently before inserting."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Self-Test Configuration Page",
                    description = "Hold the printer 'Go' or 'Info' button for 5 seconds to print a standalone configuration report. Verify internal firmware, page count, and hardware health before connecting to PC."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Initial Connection",
                    description = "Connect power cord directly to wall socket (avoid cheap surge protectors with high-voltage laser fuser heaters)."
                )
            ),
            requiredTools = listOf("Paper Ream", "OEM Power Cable", "USB Cable (A-to-B)"),
            importantWarnings = listOf(
                LessonWarning("Laser printer fusers reach temperatures over 200°C (400°F). Never touch the red or black fuser rollers inside the rear door immediately after operation.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving orange transit locks or plastic cartridge seal tapes inside the paper tray or toner bay."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Printer does not power on or trips office circuit breaker.",
                    fix = "Laser printers pull up to 1000W during initial fuser warmup. Move to a dedicated circuit or test on a known working wall outlet."
                )
            ),
            iconKey = "print"
        ),
        Lesson(
            id = "print_usb",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "USB Printer Setup & Plug-and-Play",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 10,
            explanation = "Direct USB printer connections use USB Type-A to Type-B cables. Windows uses USB Printing Support class drivers to pass spool data.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Cable Specification",
                    description = "Use a certified USB 2.0 A-to-B cable no longer than 3 meters (10 feet). Long unpowered USB cables cause packet drops and print job timeouts."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Install Driver Before Plugging In (Best Practice)",
                    description = "Many multi-function printers require installing the manufacturer software package FIRST before connecting the USB cable when prompted by the installer."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Verifying USB Port in Printer Properties",
                    description = "Open Control Panel -> Devices and Printers -> right-click printer -> Printer Properties -> Ports tab. Verify it is mapped to a Virtual Printer Port for USB (e.g., USB001)."
                )
            ),
            requiredTools = listOf("USB Type-A to Type-B Cable", "OEM Driver Package"),
            importantWarnings = listOf(
                LessonWarning("Do not use unpowered passive USB extension cables over 15 feet; printer signal attenuation will cause corrupted prints or intermittent drops.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Plugging printer into front-panel unpowered USB hubs or keyboard passthrough ports."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Windows reports 'USB Device Not Recognized'.",
                    fix = "Plug into rear motherboard USB port. In Device Manager, expand Universal Serial Bus controllers -> right-click 'USB Printing Support' -> Uninstall device, then reconnect."
                )
            ),
            iconKey = "usb"
        ),
        Lesson(
            id = "print_network",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Network Printer Setup (Standard TCP/IP Port)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 16,
            explanation = "Networked printers allow multiple office workstations to print simultaneously over LAN. Setting a permanent Static IP and using a Standard TCP/IP port prevents the printer from disappearing when DHCP leases expire.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Assign Static IP Address to Printer",
                    description = "On printer control panel or web admin page (EWS), navigate to Network -> TCP/IP -> switch from DHCP to Manual/Static (e.g. 192.168.1.200, Subnet: 255.255.255.0, Gateway: 192.168.1.1).",
                    proTip = "Alternatively, create a DHCP MAC Reservation on the office router."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Add Printer via Standard TCP/IP Port in Windows",
                    description = "Control Panel -> Devices and Printers -> Add Printer -> 'The printer that I want isn't listed' -> 'Add a printer using an IP address or hostname' -> Device Type: TCP/IP Device -> enter IP."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Disable WSD (Web Services for Devices)",
                    description = "WSD ports constantly change and cause phantom 'Printer Offline' status. Always use Raw Port 9100 on Standard TCP/IP.",
                    proTip = "In printer web page, disable WSD protocol in Network Services."
                )
            ),
            requiredTools = listOf("Ethernet Cable (Cat5e/Cat6)", "Office Subnet Info", "Web Browser"),
            importantWarnings = listOf(
                LessonWarning("Ensure the chosen Static IP is outside the router's active DHCP pool range to prevent an IP address conflict with another office device.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Allowing Windows to auto-discover printer using WSD port instead of manually creating a fixed Standard TCP/IP port."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Cannot browse to printer web GUI using IP address.",
                    fix = "Verify computer and printer are on the same subnet (e.g. both 192.168.1.x). Ping the printer IP from CMD to verify network connectivity."
                )
            ),
            iconKey = "network"
        ),
        Lesson(
            id = "print_drivers",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Printer Drivers (PCL6 vs PostScript vs Generic)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Printer drivers translate document graphics and text into Page Description Languages (PDL). Choosing between PCL6, PostScript (PS), and manufacturer universal print drivers affects formatting fidelity and print speed.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "PCL6 vs PostScript Comparison",
                    description = "PCL6: Fastest rendering for standard office documents, spreadsheets, and invoices. PostScript: Best for Adobe graphics, publishing, and precise vector typography."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Universal Print Driver (UPD)",
                    description = "HP, Canon, and Xerox offer Universal Print Drivers allowing one IT driver package to deploy across dozens of different printer models in an enterprise."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Complete Driver Removal (Print Server Properties)",
                    description = "Win + R -> type 'printui /s /t2' -> select Drivers tab -> choose driver -> click 'Remove driver and driver package'.",
                    commandOrKey = "printui /s /t2",
                    proTip = "This deletes corrupted driver .inf and .dll files from Windows DriverStore."
                )
            ),
            requiredTools = listOf("Print Management Console (printmanagement.msc)", "printui utility"),
            importantWarnings = listOf(
                LessonWarning("Do not install consumer 'Software Suites' with 800MB bloated photo editors on office workstations; install the clean 'Basic Driver' only.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving default Microsoft IPP / Class Driver which lacks duplex printing and tray selection options."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Printer outputs hundreds of blank pages with 1 line of garbled hieroglyphics.",
                    fix = "Driver language mismatch (sending PostScript raw stream to a PCL-only printer). Switch driver to standard PCL6."
                )
            ),
            iconKey = "driver"
        ),
        Lesson(
            id = "print_offline",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Printer Offline Troubleshooting & Spooler",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "The infamous 'Printer Offline' status accounts for a significant percentage of office helpdesk tickets. The issue is usually a stalled print spooler, SNMP status check error, or WSD port disconnection.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Restart Windows Print Spooler Service via CMD",
                    description = "Open CMD as Administrator and run: net stop spooler -> del /Q /F /S \"%systemroot%\\System32\\Spool\\Printers\\*.*\" -> net start spooler",
                    commandOrKey = "net stop spooler && del /Q /F /S \"%systemroot%\\System32\\Spool\\Printers\\*.*\" && net start spooler",
                    proTip = "This flushes stuck, corrupt spooler print jobs from the queue."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Disable SNMP Status Enabled",
                    description = "Printer Properties -> Ports tab -> Select TCP/IP Port -> Configure Port -> UNCHECK 'SNMP Status Enabled'.",
                    proTip = "When SNMP is checked, any slight delay in printer ping makes Windows report printer as Offline."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Uncheck 'Use Printer Offline'",
                    description = "Double-click printer queue -> Printer menu -> make sure 'Use Printer Offline' and 'Pause Printing' are NOT checked."
                )
            ),
            requiredTools = listOf("Elevated Command Prompt", "Services Console (services.msc)"),
            importantWarnings = listOf(
                LessonWarning("Deleting files from %systemroot%\\System32\\Spool\\Printers\\ permanently removes all pending queued documents for all local printers.", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Restarting the computer repeatedly without clearing the stuck corrupted print document in the spool directory."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Print Spooler service stops immediately after being started.",
                    fix = "A corrupt third-party printer driver dll is crashing spoolsv.exe. Open regedit -> HKLM\\SYSTEM\\CurrentControlSet\\Control\\Print\\Environments\\Windows x64\\Drivers -> remove culprit."
                )
            ),
            iconKey = "alert"
        ),
        Lesson(
            id = "print_paper_jam",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Paper Jam Extraction & Roller Maintenance",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 10,
            explanation = "Paper jams occur when pickup rollers slip, paper paths become obstructed, or fuser rollers crease damp paper. Improper removal tears paper and damages microswitches.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Golden Rule: Pull in the Direction of Paper Path",
                    description = "Always gently pull jammed paper in the direction of the normal paper feed path using TWO HANDS. Never yank backwards against gears."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Inspect Entire Path",
                    description = "Check: 1) Input Tray, 2) Registration roller area, 3) Under toner/drum assembly, 4) Duplexer unit, 5) Rear fuser exit door."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Cleaning Rubber Pickup Rollers",
                    description = "Smooth, glazed rubber pickup rollers cause feed slip errors. Clean rollers with a cloth lightly dampened with water or rubber renue cleaner."
                )
            ),
            requiredTools = listOf("Flashlight / Phone Light", "Lint-free cloth", "Water / Rubber Rejuvenator"),
            importantWarnings = listOf(
                LessonWarning("Never use metal scissors, screwdrivers, or tweezers to pull paper from fuser rollers. Metal tools scratch the Teflon fuser sleeve, ruining the printer!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Ripping a sheet in half and leaving a tiny triangle of paper covering an optical paper sensor flag, causing permanent false 'Paper Jam' errors."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Printer constantly displays 'Paper Jam' even though zero paper is inside.",
                    fix = "A mechanical sensor flag (tiny black plastic lever) is stuck or its spring has dislodged. Inspect paper path with flashlight and gently flick flags."
                )
            ),
            iconKey = "page"
        ),
        Lesson(
            id = "print_toner",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Toner vs Drum Units & Cartridge Issues",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "In laser printers, toner is fine plastic/iron powder. The Imaging Drum (OPC drum) uses electrostatic charges to attract toner onto paper before fuser melting.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Toner Cartridge vs Drum Unit",
                    description = "HP/Canon often combine Toner + Drum in one unit. Brother/Lexmark use separate toner cartridges and drum units (e.g. TN-760 toner clicks inside DR-730 drum)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Toner Microchip & Contacts",
                    description = "Inspect the small golden smart chip on the cartridge and matching spring pins inside the printer chassis. Clean with isopropyl alcohol."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Reset Drum Life Counter",
                    description = "Replacing a drum unit requires resetting the page counter on the printer keypad (e.g. Brother: Open front door, hold 'OK' for 2 seconds, press Up arrow to reset)."
                )
            ),
            requiredTools = listOf("OEM Toner / Drum", "Dry micro-fiber cloth"),
            importantWarnings = listOf(
                LessonWarning("Never expose the green/blue drum roller to direct sunlight or room light for more than 2 minutes; photosensitive drums become permanently light-damaged.", isDataDestructive = false),
                LessonWarning("Never vacuum spilled toner with a standard home vacuum cleaner; microfine toner dust passes through normal filters into the motor, sparking a dust explosion!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Replacing the toner cartridge when the machine is warning 'Replace Drum', or vice-versa."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Printer displays 'Toner Low' or 'Cartridge Not Recognized' on brand new replacement.",
                    fix = "Check if client purchased third-party non-OEM cartridge without a smart chip. Transfer chip from old OEM cartridge or enable 'Continue Mode' in settings."
                )
            ),
            iconKey = "ink"
        ),
        Lesson(
            id = "print_quality",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Print Quality Diagnostics (Streaks, Fading, Smudges)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "Print quality flaws follow repeatable physical patterns. Calculating the distance between recurring spots pinpoints the exact failing roller.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Diagnosing Repeating Marks (Defect Ruler)",
                    description = "Measure the distance between repeating dots or smudges down the page: Drum circumference (~75-95mm), Developer roller (~30-40mm), Fuser roller (~78mm). Match with service manual ruler."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Faded or Very Light Prints",
                    description = "Low toner, dirty laser scanner optical glass, or transfer roller charge contact oxidation. Remove toner and clean mirror with cotton swab."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Smudged Toner that Wipes Off with Finger",
                    description = "Fuser failure. The fuser heater is not reaching 200°C or pressure roller has delaminated, failing to bake toner into paper fibers."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Performing a Half-Way Stop Test",
                    description = "Print a heavy test page. Open printer door the instant the sheet enters under the toner cartridge. If the image on the drum is already flawed, the fault is cartridge/laser; if flawed only after exit, the fuser is defective."
                )
            ),
            requiredTools = listOf("Millimeter Ruler", "Service Defect Ruler Guide", "Cotton Swabs"),
            importantWarnings = listOf(
                LessonWarning("Do not use alcohol on rubber transfer rollers (spongy black roller in base); it strips electrostatic conductive coating.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Using heavy cardstock paper without changing driver paper type settings to 'Thick/Heavy', causing cold fuser smear."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Solid black line running straight down the length of every page.",
                    fix = "A scratch or gouge on the green drum surface or a chipped cleaning wiper blade. Replace imaging drum."
                )
            ),
            iconKey = "view"
        ),
        Lesson(
            id = "print_scanner",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Scanner Setup (WIA vs TWAIN & Scan to Folder SMB)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Modern office Multi-Function Printers (MFPs) scan via desktop protocols (TWAIN/WIA) or direct network push (Scan-to-Folder SMB and Scan-to-Email SMTP).",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "TWAIN vs WIA Drivers",
                    description = "TWAIN: Comprehensive driver standard with advanced controls (DPI, color profiles, descreening). WIA: Windows Image Acquisition, native Windows plug-in."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Configure Scan to Folder (SMB)",
                    description = "Create a folder on client PC (e.g. C:\\Scans). Right click -> Properties -> Sharing -> Share with 'Everyone' (Read/Write). In Advanced Sharing -> Permissions: Allow Full Control."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Configure Printer Web Address Book Entry",
                    description = "In printer web page -> Address Book -> Add SMB: Host IP (192.168.1.50), Share path ('Scans'), Port 445, PC username and local password.",
                    proTip = "Windows 10/11 requires SMBv2/v3 support. Older copiers supporting only SMBv1 will fail."
                )
            ),
            requiredTools = listOf("Printer Web Portal (EWS)", "Windows Shared Folder"),
            importantWarnings = listOf(
                LessonWarning("Never create user accounts with empty/blank passwords for SMB scanning; Windows network security blocks blank password access over SMB by default.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Forgetting to allow 'File and Printer Sharing (SMB-In)' in Windows Defender Firewall."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Scanner returns error: 'Server Connection Error / Authentication Failed'.",
                    fix = "Verify PC IP address has not changed, ensure computer user account has a password, and verify computer network profile is set to 'Private Network'."
                )
            ),
            iconKey = "scanner"
        ),
        Lesson(
            id = "print_sharing",
            categoryId = "printers",
            categoryTitle = "Printers",
            title = "Windows Printer Sharing & Network Permissions",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 12,
            explanation = "Sharing a USB-connected printer across small office workstations using Windows File & Printer Sharing (SMB).",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Enable Printer Sharing on Host PC",
                    description = "Printer Properties -> Sharing tab -> check 'Share this printer' -> assign clean share name (e.g., 'ReceptionHP')."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Adjust Advanced Sharing Settings",
                    description = "Network and Sharing Center -> Advanced sharing settings -> Private -> Turn on 'File and printer sharing'. In All Networks -> Turn off password protected sharing if trusted LAN."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Connect from Client Workstation",
                    description = "On other PC: Press Win + R -> type \\\\HOST_PC_NAME or \\\\192.168.1.25 -> right-click shared printer -> click 'Connect'. Windows installs the pushed driver."
                )
            ),
            requiredTools = listOf("Host PC", "Client PC", "Active Local Network"),
            importantWarnings = listOf(
                LessonWarning("If the Host PC is put to sleep or turned off, all other office computers lose ability to print.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Attempting to share between 32-bit and 64-bit Windows without installing additional x86/x64 drivers in the 'Additional Drivers' dialog."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Client receives Windows Error 0x0000011b when connecting to shared printer.",
                    fix = "Known Windows Print Nightmare RPC security patch issue. Create DWORD 'RpcAuthnLevelPrivacyEnabled' = 0 in HKLM\\System\\CurrentControlSet\\Control\\Print on host PC."
                )
            ),
            iconKey = "share"
        )
    )
}
