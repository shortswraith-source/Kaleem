package com.example.data.datasource

import com.example.data.model.ITTool
import com.example.data.model.ToolCategory

object ToolsDataSource {
    fun getTools(): List<ITTool> = listOf(
        ITTool(
            id = "tool_rufus",
            name = "Rufus",
            category = ToolCategory.SOFTWARE,
            whatItDoes = "Formats and converts standard ISO disk images into bootable USB flash drives, supporting UEFI (GPT) and legacy BIOS (MBR) partitioning schemes.",
            whenToUseIt = "When creating a bootable installation media for Windows 10/11, Linux, or FreeDOS diagnostic utilities.",
            basicInstructions = listOf(
                "1. Connect target USB flash drive (minimum 8GB capacity).",
                "2. Launch Rufus executable as Administrator.",
                "3. Click 'SELECT' and locate the downloaded Windows/Linux ISO file.",
                "4. Select 'Partition scheme: GPT' and 'Target system: UEFI' for modern PCs.",
                "5. Click START and confirm format warning."
            ),
            safetyNotes = "DATA HAZARD: Rufus will completely format and destroy all pre-existing files on the selected flash drive. Ensure the drive letter matches your USB.",
            isDataHazard = true
        ),
        ITTool(
            id = "tool_ventoy",
            name = "Ventoy",
            category = ToolCategory.SOFTWARE,
            whatItDoes = "An open-source multi-boot utility that formats a flash drive once with a specialized bootloader. You can drop dozens of different ISO files directly onto the drive without reformatting.",
            whenToUseIt = "When building an all-in-one technician multi-boot USB containing Windows 10, Windows 11, Ubuntu, Hiren's BootCD, and MemTest86 on a single flash drive.",
            basicInstructions = listOf(
                "1. Launch Ventoy2Disk installer on PC.",
                "2. Select target high-capacity USB drive (64GB - 128GB).",
                "3. Click 'Install' (formats drive once).",
                "4. Copy and paste raw .iso files directly onto the USB in File Explorer.",
                "5. Boot target computer from USB to display graphical ISO selection menu."
            ),
            safetyNotes = "Initial installation wipes the flash drive. Subsequent ISO updates require zero formatting and preserve other files.",
            isDataHazard = true
        ),
        ITTool(
            id = "tool_crystaldiskinfo",
            name = "CrystalDiskInfo",
            category = ToolCategory.SOFTWARE,
            whatItDoes = "Monitors and reports drive telemetry using S.M.A.R.T. technology. Reads bad sector counts, operational hours, drive temperature, and overall health status.",
            whenToUseIt = "Whenever a customer reports slow computer performance, 100% disk usage, clicking sounds, or during routine health audits.",
            basicInstructions = listOf(
                "1. Download and run CrystalDiskInfo Portable.",
                "2. Check the Health Status badge at the top: Blue = Good, Yellow = Caution, Red = Bad.",
                "3. Inspect attributes: '05 Reallocated Sectors Count' and 'C5 Current Pending Sector Count'.",
                "4. Switch raw values to decimal via Function -> Advanced Feature -> Raw Values -> 10 [DEC]."
            ),
            safetyNotes = "100% safe and read-only. Does not modify or erase any drive data.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_cpuz",
            name = "CPU-Z",
            category = ToolCategory.SOFTWARE,
            whatItDoes = "Detects motherboard model, BIOS revision date, CPU socket type, clock speeds, and RAM memory timings/channels.",
            whenToUseIt = "When verifying hardware specifications before purchasing upgrades, or verifying if dual-channel memory is actively running.",
            basicInstructions = listOf(
                "1. Launch CPU-Z.",
                "2. Check 'Mainboard' tab to identify exact motherboard model and current BIOS version.",
                "3. Check 'Memory' tab to confirm 'Channel #' shows 2x64-bit (Dual Channel).",
                "4. Check 'SPD' tab to verify manufacturer RAM part numbers and XMP profiles."
            ),
            safetyNotes = "Read-only hardware interrogator. Completely safe.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_ddu",
            name = "Display Driver Uninstaller (DDU)",
            category = ToolCategory.SOFTWARE,
            whatItDoes = "Deep-cleans all graphics driver files, registry entries, and driver cache files from Nvidia, AMD, and Intel GPUs.",
            whenToUseIt = "When graphics drivers crash with BSODs, screen blackouts occur, or when upgrading from Nvidia to AMD (or vice-versa).",
            basicInstructions = listOf(
                "1. Boot Windows into Safe Mode (mandatory).",
                "2. Launch Display Driver Uninstaller.",
                "3. Select device type: GPU -> select manufacturer (Nvidia/AMD).",
                "4. Click 'Clean and restart'.",
                "5. After reboot, install latest official WHQL driver."
            ),
            safetyNotes = "Must be run in Windows Safe Mode to prevent Windows Update from automatically installing a generic driver while cleaning.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_multimeter",
            name = "Digital Multimeter (DMM)",
            category = ToolCategory.HARDWARE,
            whatItDoes = "Measures AC/DC voltage, electrical resistance, and audio continuity to diagnose power adapters, motherboard rails, and broken wires.",
            whenToUseIt = "When testing CCTV 12V power adapters under load, checking PC power supply +12V/+5V rails, and verifying cable continuity.",
            basicInstructions = listOf(
                "1. Set dial to DC Volts (V with straight and dashed line).",
                "2. Plug Black probe into COM and Red probe into V/Ω.",
                "3. For barrel jacks: Touch Red probe to inner pin (+) and Black probe to outer sleeve (-).",
                "4. For PSU 24-pin: Measure Yellow (+12V), Red (+5V), Orange (+3.3V) against Black ground."
            ),
            safetyNotes = "ELECTRICAL SAFETY: Never measure wall AC mains power unless qualified. Never switch to Amps mode while measuring voltage, which creates a dead short.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_rj45_crimper",
            name = "RJ45 Crimper & Cable Tester",
            category = ToolCategory.HARDWARE,
            whatItDoes = "Strips cable jackets, crimps 8P8C modular gold pins through copper conductors, and tests electrical continuity across all 8 pins.",
            whenToUseIt = "When making custom-length network patch cables, repairing broken RJ45 clips, or terminating wall jacks.",
            basicInstructions = listOf(
                "1. Strip 1.5 inches of outer cable jacket with the round stripper notch.",
                "2. Untwist and arrange conductors in T568B order: W-O, O, W-G, BL, W-BL, G, W-BR, BR.",
                "3. Trim flush to 1/2 inch and insert firmly into RJ45 connector.",
                "4. Squeeze ratcheting crimp tool handle until it clicks and releases.",
                "5. Test both ends with continuity tester; verify LEDs 1-8 sequence in order."
            ),
            safetyNotes = "Ensure pass-through blades are sharp; dull blades can leave tiny copper whiskers that short adjacent pins.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_esd_strap",
            name = "Anti-Static ESD Wrist Strap",
            category = ToolCategory.HARDWARE,
            whatItDoes = "Safely bleeds electrostatic charges from the technician's body to ground through a built-in 1-megaohm resistor, preventing static zaps.",
            whenToUseIt = "Whenever handling bare microprocessors, RAM modules, or motherboards on repair benches.",
            basicInstructions = listOf(
                "1. Wrap elastic band snugly around your wrist with metal sensor against skin.",
                "2. Attach the alligator clip to an unpainted metal section of the grounded PC case chassis.",
                "3. Keep the PC power supply plugged into a grounded wall outlet with the PSU rocker switch turned OFF."
            ),
            safetyNotes = "Ensures expensive 3nm/5nm transistors are not destroyed by invisible 3,000-volt human static discharges.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_thermal_paste",
            name = "Thermal Compound & 99% Isopropanol",
            category = ToolCategory.HARDWARE,
            whatItDoes = "Fills microscopic air voids between the CPU heat spreader and cooler base with high thermal conductivity particles (zinc oxide / aluminum).",
            whenToUseIt = "Every time a CPU heatsink or liquid cooler is removed or reinstalled.",
            basicInstructions = listOf(
                "1. Clean all dried, crusty old compound using 99% Isopropyl Alcohol and lint-free wipes.",
                "2. Ensure surfaces are dry and mirror clean.",
                "3. Dispense a pea-sized dot (~4mm) of non-conductive paste in the center of the CPU.",
                "4. Fasten cooler bracket in an alternating cross pattern."
            ),
            safetyNotes = "Never use conductive liquid metal on aluminum heatsinks (causes immediate gallium embrittlement). Always use non-conductive ceramic/carbon pastes.",
            isDataHazard = false
        ),
        ITTool(
            id = "tool_sata_enclosure",
            name = "USB 3.0 SATA & NVMe Enclosure Adapter",
            category = ToolCategory.HARDWARE,
            whatItDoes = "Allows technicians to connect internal 2.5\"/3.5\" SATA drives or M.2 NVMe SSDs to any laptop via USB to extract customer data.",
            whenToUseIt = "When a computer motherboard or power supply dies, but the customer needs their photos, documents, and databases recovered immediately.",
            basicInstructions = listOf(
                "1. Remove storage drive from client's broken computer.",
                "2. Slide drive into the USB enclosure or docking bay.",
                "3. Connect USB cable to technician bench PC (use 12V power brick for 3.5\" drives).",
                "4. Open File Explorer -> Navigate to \\Users\\CustomerName -> Copy Desktop and Documents."
            ),
            safetyNotes = "Take ownership of NTFS folders if prompted by Windows with 'You don't currently have permission to access this folder'.",
            isDataHazard = false
        )
    )
}
