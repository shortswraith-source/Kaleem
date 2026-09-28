package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.Lesson
import com.example.data.model.LessonStep
import com.example.data.model.LessonWarning
import com.example.data.model.TroubleshootingItem

object SoftwareLessonsDataSource {
    fun getLessons(): List<Lesson> = listOf(
        Lesson(
            id = "soft_devmgmt",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Device Manager & Error Code Remediation",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Device Manager (devmgmt.msc) displays a graphical view of installed hardware devices and driver states, allowing technicians to identify missing drivers and hardware resource conflicts.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Accessing Device Manager",
                    description = "Press Win + X -> select Device Manager, or press Win + R and run devmgmt.msc.",
                    commandOrKey = "devmgmt.msc"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Decoding Error Codes",
                    description = "Code 28: Driver is not installed. Code 43: Device driver reported a failure or hardware crashed. Code 10: Device cannot start. Code 12: Device cannot find enough free resources."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Manual .INF Driver Installation",
                    description = "Right-click device -> Update driver -> 'Browse my computer for drivers' -> 'Let me pick from a list' -> 'Have Disk...' -> browse to the extracted .inf setup file."
                )
            ),
            requiredTools = listOf("Device Manager", "Extracted Driver Package (.inf)"),
            importantWarnings = listOf(
                LessonWarning("Disabling 'System Devices' (such as PCI Express Root Complex) will cause Windows to freeze and require a hard reboot.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Clicking 'Search automatically for drivers' on an offline computer without downloading OEM driver packages onto a flash drive first."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Nvidia / AMD GPU shows Yellow Triangle with Code 43.",
                    fix = "Code 43 indicates driver crash or failing VRAM. Run DDU in Safe Mode, clean install latest WHQL driver. If code persists, GPU chip is physically failing."
                )
            ),
            iconKey = "settings"
        ),
        Lesson(
            id = "soft_diskmgmt",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Disk Management Masterclass",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Managing physical storage drives, converting partition styles between MBR and GPT, changing drive letters, and repairing RAW file systems.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Changing Drive Letters & Paths",
                    description = "Right-click partition -> 'Change Drive Letter and Paths...'. Assign clean sequential letters (e.g., D: for Data, E: for Optical, F: for Backups).",
                    commandOrKey = "diskmgmt.msc"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Handling RAW File Systems",
                    description = "When a partition turns 'RAW', Windows cannot read the NTFS Master File Table (MFT). Do NOT click format! Run testdisk or chkdsk to restore the boot sector."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Converting MBR to GPT Non-Destructively",
                    description = "Use Windows built-in MBR2GPT tool: In elevated CMD run: mbr2gpt /validate /allowFullOS followed by mbr2gpt /convert /allowFullOS.",
                    commandOrKey = "mbr2gpt /convert /allowFullOS"
                )
            ),
            requiredTools = listOf("Disk Management GUI", "Elevated Command Prompt"),
            importantWarnings = listOf(
                LessonWarning("DATA HAZARD: When Windows pops up 'You need to format the disk before you can use it', NEVER click Format if the drive contains important customer files!", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Converting disk to Dynamic Disk, which breaks multi-boot environments and complex partition tools."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Drive letter conflicts with mapped network share.",
                    fix = "Assign high letters like M:, P:, or Z: for network shares to avoid collisions with removable USB flash drives."
                )
            ),
            iconKey = "storage"
        ),
        Lesson(
            id = "soft_taskmgr",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Task Manager & Resource Optimization",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Task Manager provides deep real-time monitoring of CPU, RAM, Disk, and Network usage. It is the primary tool for identifying malware miners and frozen processes.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Direct Shortcut Keys",
                    description = "Press Ctrl + Shift + Esc to open Task Manager directly without going through the Windows security lock screen.",
                    commandOrKey = "Ctrl + Shift + Esc"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Startup Apps Management",
                    description = "Navigate to Startup Apps tab. Sort by 'Startup Impact'. Disable high-impact unnecessary software (Spotify, Discord, Adobe Updaters) to cut boot times by 50%."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Resource Monitor (resmon)",
                    description = "Click 'Performance' -> 'Open Resource Monitor'. View Disk queue lengths and which specific files (Handle search) are locking folders."
                )
            ),
            requiredTools = listOf("Task Manager", "Resource Monitor (resmon.exe)"),
            importantWarnings = listOf(
                LessonWarning("Terminating 'csrss.exe' or 'smss.exe' will immediately trigger a CRITICAL_PROCESS_DIED Blue Screen crash as they are essential Windows kernel subsystems.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Ending background services without checking their description, breaking print spoolers or network adapters."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Windows Explorer freezes, taskbar is unresponsive.",
                    fix = "Open Task Manager (Ctrl+Shift+Esc), find 'Windows Explorer' under Processes, right-click and click 'Restart'."
                )
            ),
            iconKey = "speed"
        ),
        Lesson(
            id = "soft_rufus",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Rufus Advanced Techniques & ISO Burning",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Rufus is the industry-standard lightweight utility for formatting and creating bootable USB flash drives from ISO images.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "GPT vs MBR Target Selection",
                    description = "GPT is required for modern UEFI systems and Windows 11. MBR is for legacy BIOS PCs."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Windows 11 Setup Customizer",
                    description = "Rufus can automatically create a local account with matching name, bypass TPM/Secure Boot, and disable telemetry questions."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Creating Windows To Go Drives",
                    description = "Rufus allows installing a full portable Windows environment directly onto an external USB SSD (Windows To Go) for emergency field repairs."
                )
            ),
            requiredTools = listOf("Rufus v4.x", "Windows ISO", "Fast USB 3.2 Drive"),
            importantWarnings = listOf(
                LessonWarning("Rufus destroys all partitions on the selected USB drive. Double-check device drive letter!", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Using slow USB 2.0 drives that take 45 minutes to flash an ISO instead of USB 3.0."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Rufus error: 'Device is in use by another process'.",
                    fix = "Close all open File Explorer windows or antivirus scanning the drive, or clean disk in diskpart first."
                )
            ),
            iconKey = "usb"
        ),
        Lesson(
            id = "soft_ventoy",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Ventoy Multi-Boot Flash Drive Setup",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Ventoy eliminates formatting USB drives repeatedly. Install Ventoy ONCE, and you can simply drag-and-drop dozens of ISO files (Windows, Ubuntu, MemTest, Clonezilla) directly onto the drive.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Install Ventoy to Flash Drive",
                    description = "Download Ventoy. Select your USB drive, set Partition Scheme to GPT, and click 'Install'. Ventoy creates two partitions: a hidden VTOYEFI boot partition and an exFAT data partition."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Copy ISO Files Directly",
                    description = "Simply copy and paste your ISO files (Windows 11.iso, HirensBootCD.iso, Ubuntu.iso) into the main Ventoy drive in File Explorer."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Booting & Menu Selection",
                    description = "Boot PC from the Ventoy USB. A clean graphical boot menu appears listing every copied ISO. Select any OS and boot instantly!"
                )
            ),
            requiredTools = listOf("Ventoy installer", "32GB - 128GB USB Flash Drive"),
            importantWarnings = listOf(
                LessonWarning("Initial Ventoy installation formats the flash drive completely. Subsequent ISO additions require ZERO formatting.", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Unzipping or extracting the ISO files onto Ventoy. Ventoy requires raw untouched .iso files!"
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Ventoy fails Secure Boot verification on modern PCs.",
                    fix = "Ventoy includes an MOK (Machine Owner Key) enrollment tool on first boot. Select 'Enroll Key' -> 'ENROLL_THIS_KEY_IN_MOKLIST' -> Enter password 'ventoy'."
                )
            ),
            iconKey = "storage"
        ),
        Lesson(
            id = "soft_cpuz",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "CPU-Z Hardware Identification & Timings",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 10,
            explanation = "CPU-Z gathers information on the central processor, motherboard chipset, memory timings, and SPD profiles without opening the computer chassis.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "CPU Tab Analysis",
                    description = "Check Processor Name, Codename, TDP rating, Core Voltage (VCORE), and real-time Core Speed multiplier."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Mainboard Tab",
                    description = "Inspect Motherboard Model and current BIOS Version / Release Date to verify if an update is needed for newer CPU support."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Memory & SPD Tabs",
                    description = "Check Channels # (2 x 64-bit = Dual Channel). Inspect SPD tab to read factory XMP profiles and manufacturer module part numbers."
                )
            ),
            requiredTools = listOf("CPU-Z utility"),
            importantWarnings = listOf(
                LessonWarning("CPU-Z is read-only and 100% safe. It does not alter hardware voltages or overclock frequencies.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Reading DRAM Frequency as half the advertised speed without realizing DDR (Double Data Rate) transfers on both clock edges (e.g., 1600 MHz in CPU-Z = 3200 MT/s)."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "CPU-Z displays Channel # as 'Single' with 2 sticks installed.",
                    fix = "RAM sticks are installed in wrong slots (e.g. 1 & 2). Power down and move sticks to slots 2 and 4 (A2 & B2)."
                )
            ),
            iconKey = "chip"
        ),
        Lesson(
            id = "soft_crystaldiskinfo",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "CrystalDiskInfo S.M.A.R.T. Health Reading",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "CrystalDiskInfo monitors Self-Monitoring, Analysis and Reporting Technology (S.M.A.R.T.) to predict hard drive and SSD failure before catastrophic data loss occurs.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Overall Health Status & Temperature",
                    description = "Good (Blue), Caution (Yellow), Bad (Red). Operating temperatures should remain below 50°C for HDDs and below 70°C for NVMe SSDs."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Critical HDD Health Attributes",
                    description = "ID 05 (Reallocated Sectors Count), ID C5 (Current Pending Sector Count), ID C6 (Uncorrectable Sector Count). Any non-zero raw value is a danger sign."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Switching Raw Values to Decimals",
                    description = "By default raw values are hexadecimal. Click Function -> Advanced Feature -> Raw Values -> '10 [DEC]' to read real decimal sector counts!",
                    proTip = "This makes reading exact bad sector counts effortless."
                )
            ),
            requiredTools = listOf("CrystalDiskInfo"),
            importantWarnings = listOf(
                LessonWarning("A drive with 'Caution (Yellow)' status is in active failure. Immediately back up critical files before performing any other diagnostics!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Relying on Windows built-in disk check, which only reports drive failure after massive filesystem corruption has already occurred."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "CrystalDiskInfo displays 98% Life Remaining on SSD with 0 bad sectors.",
                    fix = "This is completely normal healthy wear. NAND cells have 98% write endurance cycles remaining."
                )
            ),
            iconKey = "hdd"
        ),
        Lesson(
            id = "soft_eventvwr",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Windows Event Viewer & Crash Analysis",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "Event Viewer (eventvwr.msc) logs comprehensive system events, application crashes, security audits, and kernel hardware failures.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Navigating to Critical Logs",
                    description = "Press Win + R -> type eventvwr.msc. Expand 'Windows Logs' -> click 'System' and 'Application'.",
                    commandOrKey = "eventvwr.msc"
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Filter by Level (Critical & Error)",
                    description = "Click 'Filter Current Log...' on right pane. Check 'Critical' and 'Error' boxes to hide thousands of benign informational notices."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Decoding Key Event IDs",
                    description = "Event ID 41 (Kernel-Power): PC shut down or restarted without cleanly shutting down first. Event ID 1001 (BugCheck): System crashed with BSOD. Event ID 7 (Disk): Bad block on device. Event ID 1000 (Application Crash)."
                )
            ),
            requiredTools = listOf("Windows Event Viewer"),
            importantWarnings = listOf(
                LessonWarning("Scammers often open Event Viewer and tell clients that normal Administrative warnings are 'hackers'. Distinguish standard background warnings from genuine recurring crashes.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Panicking over standard DCOM Event ID 10016 errors, which are harmless Microsoft permissions quirks present on every clean Windows install."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "PC suddenly reboots while gaming without any Blue Screen.",
                    fix = "Look for Event ID 41. If BugcheckCode is 0, the PSU tripped power or CPU hit thermal cutoff instantly before Windows could write a minidump."
                )
            ),
            iconKey = "view"
        ),
        Lesson(
            id = "soft_cmd_advanced",
            categoryId = "software_tools",
            categoryTitle = "Software & Tools",
            title = "Command Line & PowerShell Maintenance Scripts",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Automating IT repair workflows using command-line scripting for disk cloning, robocopy user data backups, and Windows component servicing.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Robocopy Mirroring for User Data Backups",
                    description = "robocopy C:\\Users\\Customer D:\\Backup /MIR /R:1 /W:1 /XJ /MT:8 (Mirrors data, skips symlink loops, uses 8 multi-threaded CPU cores).",
                    commandOrKey = "robocopy <src> <dest> /MIR /R:1 /W:1 /XJ /MT:8",
                    proTip = "Robocopy is faster and more reliable than File Explorer drag-and-drop."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Clearing Temporary Cache Junk via CMD",
                    description = "del /q/f/s %TEMP%\\* && del /q/f/s C:\\Windows\\Temp\\* (Frees disk space instantly).",
                    commandOrKey = "del /q/f/s %TEMP%\\*"
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "PowerShell Execution Policy",
                    description = "Set-ExecutionPolicy RemoteSigned -Scope CurrentUser to allow running technician PowerShell scripts.",
                    commandOrKey = "Set-ExecutionPolicy RemoteSigned"
                )
            ),
            requiredTools = listOf("Elevated Command Prompt / PowerShell"),
            importantWarnings = listOf(
                LessonWarning("The /MIR (Mirror) switch in Robocopy DELETES any files in destination that no longer exist in source! Use with extreme care.", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Copying C:\\Users using standard copy commands without /XJ, getting stuck in infinite Application Data junction loops."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Robocopy fails on locked Outlook .pst or browser files.",
                    fix = "Close running applications or use Volume Shadow Copy (VSS) tools to copy in-use files."
                )
            ),
            iconKey = "terminal"
        )
    )
}
