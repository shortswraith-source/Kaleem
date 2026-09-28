package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.Lesson
import com.example.data.model.LessonStep
import com.example.data.model.LessonWarning
import com.example.data.model.TroubleshootingItem

object WindowsLessonsDataSource {
    fun getLessons(): List<Lesson> = listOf(
        Lesson(
            id = "win_install",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Clean Windows 11/10 Installation",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 20,
            explanation = "A clean installation replaces the existing operating system and software environment on a storage drive with a fresh copy of Windows. This resolves severe malware infections, corrupted system registries, and system instability.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Prepare Installation Media",
                    description = "Insert a USB flash drive (minimum 8GB capacity) formatted with Windows Media Creation Tool or Rufus.",
                    proTip = "Always back up user files from C:\\Users before starting."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Access Boot Menu",
                    description = "Plug the USB into the target PC, power on, and immediately tap the Boot Menu key (F12 on Dell/Lenovo, F9 on HP, F8 on Asus). Select UEFI USB Device.",
                    commandOrKey = "F12 / F9 / F8"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Select Custom (Advanced) Installation",
                    description = "When prompted by Windows Setup, select 'Custom: Install Windows only (advanced)' instead of Upgrade.",
                    proTip = "Upgrade keeps old junk; Custom performs a genuine fresh install."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Drive Partition Selection",
                    description = "Delete previous system partitions (System Reserved, Recovery, OS Partition) on the target drive until 'Unallocated Space' remains, then click Next.",
                    proTip = "Windows automatically generates EFI (100MB), MSR (16MB), and C: partitions."
                ),
                LessonStep(
                    stepNumber = 5,
                    title = "Complete Out-of-Box Experience (OOBE)",
                    description = "Select regional keyboard, language, and user profile credentials. If setting up offline without Microsoft account, press Shift + F10 and type OOBE\\BYPASSNRO.",
                    commandOrKey = "Shift + F10 -> OOBE\\BYPASSNRO"
                )
            ),
            requiredTools = listOf("8GB+ USB Flash Drive", "Windows 11/10 ISO", "Working internet connection"),
            importantWarnings = listOf(
                LessonWarning("DATA LOSS WARNING: Deleting drive partitions permanently erases all files, photos, and databases on that volume.", isDataDestructive = true),
                LessonWarning("Ensure laptop is connected to AC wall charger during setup to prevent bricking mid-installation.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Accidentally deleting secondary D: or E: data drive partitions instead of Drive 0 OS partitions.",
                "Selecting Legacy/CSM boot mode instead of UEFI for Windows 11 (Windows 11 requires UEFI & Secure Boot).",
                "Leaving other USB drives or external HDDs connected during installation."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Setup error: 'Windows cannot be installed to this disk. The selected disk has an MBR partition table.'",
                    fix = "Convert disk to GPT: Press Shift+F10 to open CMD -> type diskpart -> select disk 0 -> clean -> convert gpt -> exit."
                ),
                TroubleshootingItem(
                    symptom = "'No drives were found' on 11th-14th Gen Intel CPUs.",
                    fix = "Download Intel Rapid Storage Technology (IRST) VMD driver onto USB, click 'Load Driver' in setup, and select the folder."
                )
            ),
            iconKey = "windows"
        ),
        Lesson(
            id = "win_bootable_usb",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Creating Bootable USB (Rufus & Ventoy)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "A bootable USB turns an official ISO disk image into an executable storage device that motherboards can boot into prior to loading the main OS.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Download Windows ISO & Rufus",
                    description = "Acquire the latest official Windows ISO from Microsoft and download Rufus from its official site (rufus.ie)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Configure Rufus Target Scheme",
                    description = "Select target USB. Choose 'Partition scheme: GPT' and 'Target system: UEFI (non-CSM)' for modern computers.",
                    proTip = "Use MBR only if installing on legacy BIOS motherboards manufactured before 2012."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Customization Options",
                    description = "Rufus offers checkboxes to remove Windows 11 requirements (TPM 2.0, Secure Boot, 4GB RAM) for older lab machines.",
                    proTip = "Only check bypass options when client hardware legitimately lacks TPM 2.0."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Write & Verify",
                    description = "Click START. Rufus will format the flash drive and copy installation files."
                )
            ),
            requiredTools = listOf("Rufus v4.x", "Official Windows ISO", "16GB USB 3.0 Drive"),
            importantWarnings = listOf(
                LessonWarning("Rufus formats the flash drive completely. Any existing files on the USB will be destroyed.", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Using cheap counterfeit flash drives that freeze at 99% extraction.",
                "Selecting MBR partition scheme for modern UEFI machines, causing the motherboard boot menu to ignore the drive."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "USB drive is write protected or fails to format.",
                    fix = "Open CMD as Administrator, run 'diskpart', 'list disk', 'select disk X', 'attributes disk clear readonly', 'clean'."
                )
            ),
            iconKey = "usb"
        ),
        Lesson(
            id = "win_bios_uefi",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "BIOS / UEFI Setup & Configuration",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "UEFI (Unified Extensible Firmware Interface) replaces legacy BIOS. It initializes motherboard hardware components (CPU, RAM, PCIe, storage) before handing execution to the operating system bootloader.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Entering UEFI Firmware Setup",
                    description = "Turn on computer and rapidly press DEL or F2. From Windows 10/11: Settings -> Recovery -> Advanced Startup -> Troubleshoot -> Advanced -> UEFI Firmware Settings.",
                    commandOrKey = "DEL / F2"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Configure Boot Priority Order",
                    description = "Navigate to the Boot tab. Move 'Windows Boot Manager' or 'UEFI USB Drive' to Position #1."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Enable Secure Boot & TPM 2.0 / fTPM",
                    description = "In Security or Advanced CPU configuration, set Intel PTT or AMD fTPM to 'Enabled'. Ensure Secure Boot is set to 'Standard'.",
                    proTip = "Windows 11 and modern games (Valorant) require both TPM 2.0 and Secure Boot active."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "SATA & NVMe Controller Mode",
                    description = "Verify SATA controller is set to AHCI mode (or NVMe RAID if configuring software striping).",
                    proTip = "Switching from IDE/RAID to AHCI after Windows is installed requires Safe Mode boot."
                ),
                LessonStep(
                    stepNumber = 5,
                    title = "Save Changes & Reset",
                    description = "Press F10 to Save & Exit. The system will reboot with the new firmware parameters.",
                    commandOrKey = "F10"
                )
            ),
            requiredTools = listOf("Wired USB Keyboard", "Motherboard Manual"),
            importantWarnings = listOf(
                LessonWarning("Never turn off power or unplug the computer while flashing/updating BIOS firmware; doing so can brick the motherboard.", isDataDestructive = false),
                LessonWarning("Changing SATA mode from RAID to AHCI on an existing OS without prep will cause INACCESSIBLE_BOOT_DEVICE BSOD.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving CSM (Compatibility Support Module) enabled when installing Windows 11.",
                "Disabling XMP/EXPO RAM profiles resulting in memory running at baseline 2133MHz instead of rated 3600MHz/6000MHz."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Monitor displays black screen, cannot enter BIOS.",
                    fix = "Reset CMOS: Unplug AC power cord, remove CR2032 coin battery for 5 minutes, or bridge CLR_CMOS jumper with screwdriver for 10 seconds."
                )
            ),
            iconKey = "chip"
        ),
        Lesson(
            id = "win_boot_menu",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Boot Menu & OEM Shortcut Keys",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 8,
            explanation = "The One-Time Boot Menu allows temporary selection of a boot device (such as a diagnostic USB or optical drive) without permanently changing BIOS boot priority.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Brand Key Reference Table",
                    description = "Dell / Alienware: F12 | HP / Compaq: F9 or Esc | Lenovo / ThinkPad: F12 or Enter | Asus: F8 or Del | Acer: F12 | MSI: F11 | Gigabyte: F12.",
                    commandOrKey = "F12, F9, F11, F8"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Timing the Key Press",
                    description = "Start tapping the boot key repeatedly the moment the power LED illuminates, before the manufacturer splash screen disappears."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Selecting Boot Option",
                    description = "Look for options prefixed with 'UEFI:'. Avoid legacy non-UEFI entries on modern systems."
                )
            ),
            requiredTools = listOf("USB Keyboard"),
            importantWarnings = listOf(
                LessonWarning("Some wireless Bluetooth keyboards do not transmit keypresses until after the OS Bluetooth stack loads. Use a 2.4GHz dongle or wired USB keyboard.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Holding the key down continuously instead of tapping rhythmically (holding can cause keyboard buffer overflow error on some BIOSes)."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Acer or Lenovo does not respond to F12.",
                    fix = "Enter BIOS (F2) -> go to Main tab -> enable 'F12 Boot Menu' (often disabled by default on Acer)."
                )
            ),
            iconKey = "keyboard"
        ),
        Lesson(
            id = "win_partitioning",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Drive Partitioning (GPT vs MBR & Sizing)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "Partitioning divides a physical drive into separate logical volumes. Modern systems use GPT (GUID Partition Table) which supports drives over 2TB and up to 128 partitions, replacing the 1983 MBR standard.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Inspect Existing Partitions in Disk Management",
                    description = "Press Win + X -> select Disk Management (diskmgmt.msc). Right-click drive name -> Properties -> Volumes tab to verify Partition Style (GPT vs MBR).",
                    commandOrKey = "diskmgmt.msc"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Anatomy of a Windows GPT Drive",
                    description = "A standard Windows 10/11 GPT installation contains: 1) EFI System Partition (~100MB, FAT32), 2) MSR (Microsoft Reserved, 16MB), 3) Windows C: Volume (NTFS), 4) WinRE Recovery Partition (~600MB-1GB)."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Shrinking & Creating Data Partitions",
                    description = "Right-click C: -> Shrink Volume. Enter amount to shrink (e.g., 100,000MB for 100GB). Right click newly unallocated space -> New Simple Volume -> format as NTFS."
                )
            ),
            requiredTools = listOf("Windows Disk Management", "Diskpart utility"),
            importantWarnings = listOf(
                LessonWarning("Never format or delete the EFI System Partition (100MB FAT32). Doing so removes the bootloader and prevents Windows from starting.", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Converting disk to Dynamic Disk accidentally. Stick to Basic Disks for normal OS and data drives."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "'Extend Volume' option is greyed out in Disk Management.",
                    fix = "Disk Management can only extend a partition if unallocated space is directly adjacent to its right side. If a Recovery partition is between them, use diskpart or a partition tool to move/recreate."
                )
            ),
            iconKey = "storage"
        ),
        Lesson(
            id = "win_drivers",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Hardware Drivers Installation & Hierarchy",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "Drivers are software bridges that allow Windows kernel to communicate directly with physical hardware components like GPUs, network adapters, chipsets, and sound processors.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Correct Driver Installation Sequence",
                    description = "Always install in this sequence: 1) Motherboard Chipset (INF), 2) Intel Management Engine / AMD PSP, 3) Network/LAN/Wi-Fi, 4) Dedicated GPU (Nvidia/AMD), 5) Audio/Bluetooth."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Checking Device Manager for Missing Drivers",
                    description = "Press Win + X -> Device Manager (devmgmt.msc). Look for Yellow Exclamation Mark (!) or 'Unknown Device'.",
                    commandOrKey = "devmgmt.msc"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Finding Hardware IDs for Unknown Devices",
                    description = "Right-click Unknown Device -> Properties -> Details tab -> select 'Hardware Ids' in dropdown. Note the VEN (Vendor) and DEV (Device) codes (e.g., VEN_10EC&DEV_8168 = Realtek PCIe GbE).",
                    proTip = "Search PCI-ID repository or vendor site with VEN and DEV string."
                )
            ),
            requiredTools = listOf("Device Manager", "Manufacturer Support Page"),
            importantWarnings = listOf(
                LessonWarning("Avoid third-party 'Driver Updater' shovelware. They frequently install wrong INF drivers, telemetry adware, or corrupt kernel stability.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Relying solely on default Windows generic display adapter instead of installing official Nvidia Studio/GameReady or AMD Adrenalin drivers."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "GPU driver crashes repeatedly or black screens during update.",
                    fix = "Boot into Safe Mode, run Display Driver Uninstaller (DDU) to wipe all residual registry keys and driver files, reboot, and clean install fresh driver."
                )
            ),
            iconKey = "driver"
        ),
        Lesson(
            id = "win_activation",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Windows Activation & Licensing Concepts",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 10,
            explanation = "Understanding OEM, Retail, and Volume (KMS/MAK) licenses helps IT technicians legally activate customer machines and troubleshoot licensing migration.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Types of Windows Licenses",
                    description = "1) OEM (Original Equipment Manufacturer): Tied permanently to motherboard hardware hash, non-transferable. 2) Retail: Full license, transferable to a new PC build. 3) Volume (KMS/MAK): Corporate business licensing."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Reading OEM Key from Motherboard Firmware",
                    description = "Open CMD as Admin and type: wmic path softwarelicensingservice get OA3xOriginalProductKey",
                    commandOrKey = "wmic path softwarelicensingservice get OA3xOriginalProductKey"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Slmgr Management Commands",
                    description = "Check license expiration: slmgr /xpr | Check license details: slmgr /dli | Uninstall key: slmgr /upk | Clear registry key: slmgr /cpky",
                    commandOrKey = "slmgr /xpr"
                )
            ),
            requiredTools = listOf("Command Prompt (Admin)", "Microsoft Account"),
            importantWarnings = listOf(
                LessonWarning("Replacing motherboard on an OEM licensed machine will revoke activation. Advise clients before motherboard swaps.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Installing Windows 10/11 Pro on a laptop that has a built-in Windows Home OEM digital license without a Pro product key."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Activation Error 0xC004C003 (Product Key Blocked).",
                    fix = "Run Windows Activation Troubleshooter in Settings -> System -> Activation -> Troubleshoot. If hardware changed, click 'I changed hardware on this device recently'."
                )
            ),
            iconKey = "key"
        ),
        Lesson(
            id = "win_troubleshooting",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "System File Repair (SFC & DISM)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "SFC (System File Checker) and DISM (Deployment Image Servicing and Management) repair damaged core Windows system binaries and Component Store without requiring an OS reinstall.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Open Elevated Command Prompt",
                    description = "Press Win + S, type 'cmd', right-click and choose 'Run as administrator'.",
                    commandOrKey = "cmd (Admin)"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Run DISM Component Store Health Check",
                    description = "Type: DISM /Online /Cleanup-Image /ScanHealth to detect store corruption.",
                    commandOrKey = "DISM /Online /Cleanup-Image /ScanHealth"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Repair Component Store",
                    description = "Type: DISM /Online /Cleanup-Image /RestoreHealth to download fresh replacement files from Windows Update servers.",
                    commandOrKey = "DISM /Online /Cleanup-Image /RestoreHealth",
                    proTip = "Allow 5-15 minutes. It may pause at 62.3% while verifying manifests."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Execute System File Checker (SFC)",
                    description = "Type: sfc /scannow. SFC scans protected operating system files and replaces corrupted files with cached copies from the healthy component store.",
                    commandOrKey = "sfc /scannow"
                )
            ),
            requiredTools = listOf("Elevated Command Prompt", "Stable Internet Connection"),
            importantWarnings = listOf(
                LessonWarning("Do not force reboot or terminate command prompt while DISM RestoreHealth is writing to the component store.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Running SFC before DISM. If the component store cache itself is corrupt, SFC will fail to repair files. Always run DISM first."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "'DISM error 0x800f081f: The source files could not be found.'",
                    fix = "Point DISM to a mounted Windows ISO install.wim: DISM /Online /Cleanup-Image /RestoreHealth /Source:WIM:D:\\sources\\install.wim:1 /LimitAccess"
                )
            ),
            iconKey = "repair"
        ),
        Lesson(
            id = "win_bsod",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Blue Screen of Death (BSOD) Diagnostics",
            difficulty = DifficultyLevel.ADVANCED,
            estimatedTimeMinutes = 18,
            explanation = "A Blue Screen (BugCheck) occurs when the Windows NT kernel encounters a fatal condition from which it cannot safely recover without risking memory or file system corruption.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Decoding Common Stop Codes",
                    description = "MEMORY_MANAGEMENT (Faulty RAM) | CRITICAL_PROCESS_DIED (Corrupted system component) | INACCESSIBLE_BOOT_DEVICE (Storage driver/SATA mode) | DRIVER_IRQL_NOT_LESS_OR_EQUAL (Faulty third-party driver) | WHEA_UNCORRECTABLE_ERROR (Hardware/CPU voltage)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Locate Minidump Files",
                    description = "Windows writes crash crashdumps to C:\\Windows\\Minidump\\*.dmp. Copy them to desktop for analysis.",
                    proTip = "Use BlueScreenView or WinDbg Preview to open the .dmp file."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Identify Faulting Module (.sys)",
                    description = "In WinDbg run !analyze -v. Examine 'MODULE_NAME' and 'IMAGE_NAME'. If nvlddmkm.sys crashes, it's Nvidia GPU driver; if netwtw10.sys, Intel Wi-Fi driver.",
                    commandOrKey = "!analyze -v"
                )
            ),
            requiredTools = listOf("BlueScreenView / WinDbg", "MemTest86 USB", "Command Prompt"),
            importantWarnings = listOf(
                LessonWarning("Repeated WHEA_UNCORRECTABLE_ERROR often signals imminent failure of an NVMe SSD or failing CPU core. Back up data immediately!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Reinstalling Windows repeatedly without testing hardware RAM modules when stop code is MEMORY_MANAGEMENT."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "BSOD INACCESSIBLE_BOOT_DEVICE after changing BIOS settings.",
                    fix = "Re-enter BIOS and toggle SATA Mode from RAID back to AHCI (or vice-versa)."
                )
            ),
            iconKey = "error"
        ),
        Lesson(
            id = "win_startup_repair",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Startup Repair & BCD Rebuild",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "When Windows fails to boot due to a damaged Boot Configuration Data (BCD) store or missing boot records, WinRE command-line tools can reconstruct the bootloader.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Boot into Windows Recovery Environment (WinRE)",
                    description = "Interrupt booting three consecutive times by holding power button, or boot from Windows USB and choose 'Repair your computer' -> Troubleshoot -> Command Prompt."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Identify Volume Drive Letters in Diskpart",
                    description = "Type 'diskpart', then 'list vol'. Note the letter assigned to Windows OS (often D: in WinRE) and the 100MB FAT32 EFI partition.",
                    commandOrKey = "diskpart -> list vol"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Reconstruct Boot Configuration (UEFI)",
                    description = "Assign letter S to EFI partition in diskpart. Exit diskpart, then type: bcdboot D:\\Windows /s S: /f UEFI",
                    commandOrKey = "bcdboot D:\\Windows /s S: /f UEFI",
                    proTip = "This creates clean EFI boot files directly into the FAT32 boot partition."
                )
            ),
            requiredTools = listOf("Windows Installation USB", "Command Prompt WinRE"),
            importantWarnings = listOf(
                LessonWarning("Be careful when running diskpart commands in WinRE; drive letters in recovery environment frequently differ from normal C: and D:.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Running legacy 'bootrec /fixmbr' on modern UEFI GPT disks. Bootrec MBR commands do not work on UEFI systems."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "bcdboot returns: 'Failure when attempting to copy boot files'.",
                    fix = "Format the 100MB EFI partition as FAT32: 'format fs=fat32 quick label=EFI' in diskpart, then retry bcdboot."
                )
            ),
            iconKey = "boot"
        ),
        Lesson(
            id = "win_disk_mgmt",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Disk Management & Diskpart CLI",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Disk Management GUI (diskmgmt.msc) and the diskpart CLI are essential tools for initializing raw storage drives, formatting file systems, and resolving offline disk signatures.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Initializing a Brand New SSD/HDD",
                    description = "When a new drive is installed, Disk Management will display 'Disk Unknown, Not Initialized'. Right-click and choose 'Initialize Disk' -> select GPT.",
                    commandOrKey = "diskmgmt.msc"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Understanding File Systems",
                    description = "NTFS: Default for Windows OS and internal drives (supports permissions & files > 4GB). exFAT: Best for external drives shared with macOS/Linux. FAT32: Legacy, limited to 4GB per file."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Diskpart Deep Cleaning",
                    description = "CMD Admin -> diskpart -> list disk -> select disk X -> clean. This wipes partition table and sector headers.",
                    commandOrKey = "clean",
                    proTip = "Use 'clean' when USB drives or SSDs refuse to format in GUI."
                )
            ),
            requiredTools = listOf("Disk Management", "Command Prompt"),
            importantWarnings = listOf(
                LessonWarning("CRITICAL: The 'clean' command in diskpart instantly wipes all partition headers on the selected disk. Verify 'select disk' number twice!", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Formatting client USB flash drives with NTFS instead of exFAT when they need to transfer files to Macbooks."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Disk is showing 'Offline (Signature Collision)'.",
                    fix = "In Disk Management, right-click the grey Disk box on the left and click 'Online'. Windows will automatically generate a new unique disk GUID."
                )
            ),
            iconKey = "hdd"
        ),
        Lesson(
            id = "win_cmd_basics",
            categoryId = "windows",
            categoryTitle = "Windows OS",
            title = "Essential CMD & PowerShell for Techs",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "Command-line tools allow technicians to diagnose hardware specs, kill frozen background processes, schedule reboots, and verify storage health faster than GUI menus.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Hardware & System Info",
                    description = "Type 'systeminfo' for BIOS date, OS build, and network adapters. Type 'wmic memorychip get speed,capacity' to inspect RAM without opening the chassis.",
                    commandOrKey = "systeminfo"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Disk Health & Verification",
                    description = "Type 'chkdsk C: /f /r' to find file system anomalies and bad sectors. Schedule check on next reboot.",
                    commandOrKey = "chkdsk C: /f /r"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Process Termination",
                    description = "Type 'tasklist' to see running PID numbers. Type 'taskkill /F /IM excel.exe' or 'taskkill /PID 4210 /F' to force kill locked applications.",
                    commandOrKey = "taskkill /F /IM <name>.exe"
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Immediate Reboot or Shutdown",
                    description = "Type 'shutdown /r /t 0' for instant reboot, or 'shutdown /r /o /t 0' to boot straight into Advanced Recovery.",
                    commandOrKey = "shutdown /r /t 0"
                )
            ),
            requiredTools = listOf("Command Prompt / PowerShell"),
            importantWarnings = listOf(
                LessonWarning("Running 'chkdsk /r' on an SSD that has failing NAND flash can accelerate wear. Always backup crucial files before running chkdsk /r on questionable drives.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Running CMD in non-elevated user mode instead of right-clicking and choosing 'Run as Administrator'."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Command Prompt closes immediately or access is denied.",
                    fix = "Launch via Win + R -> type cmd -> press Ctrl + Shift + Enter to open elevated prompt."
                )
            ),
            iconKey = "terminal"
        )
    )
}
