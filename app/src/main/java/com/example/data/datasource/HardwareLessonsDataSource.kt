package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.Lesson
import com.example.data.model.LessonStep
import com.example.data.model.LessonWarning
import com.example.data.model.TroubleshootingItem

object HardwareLessonsDataSource {
    fun getLessons(): List<Lesson> = listOf(
        Lesson(
            id = "hw_cpu",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "CPU Sockets, Thermal Paste & Coolers",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 15,
            explanation = "The Central Processing Unit (CPU) is the computer's primary calculation engine. Understanding socket types (Intel LGA vs AMD PGA/LGA), thermal interfaces, and cooler mounting prevents fatal pin damage.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Identify Socket Architecture",
                    description = "Intel uses LGA (pins on motherboard, flat pads on CPU). AMD AM4 uses PGA (pins on CPU), while AM5 uses LGA. Match golden alignment triangles exactly before seating.",
                    proTip = "Never apply force. CPUs drop in with zero insertion force (ZIF)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Thermal Paste Application",
                    description = "Apply a pea-sized dot (~4mm) or an 'X' pattern of non-conductive thermal paste in the center of the CPU IHS (Integrated Heat Spreader).",
                    proTip = "Mounting pressure from the cooler spreads the paste evenly across the die."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Mount Cooler Evenly",
                    description = "Tighten cooler screws in a diagonal cross pattern (top-left, bottom-right, top-right, bottom-left) in gradual turns to avoid uneven pressure that bends pins or causes uneven contact."
                )
            ),
            requiredTools = listOf("Cross-head Screwdriver", "Thermal Compound (Arctic MX-4/Noctua NT-H1)", "99% Isopropyl Alcohol", "Lint-free Wipes"),
            importantWarnings = listOf(
                LessonWarning("Never touch motherboard LGA socket pins with your fingers or cloth. Bent LGA pins can permanently disable dual-channel memory or brick the board.", isDataDestructive = false),
                LessonWarning("Remember to peel the transparent plastic protective sticker off the bottom of the CPU heatsink prior to mounting!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving the heatsink protective plastic peel on, resulting in instant 100°C thermal throttling.",
                "Using conductive liquid metal on aluminum heatsinks (gallium destroys aluminum via liquid metal embrittlement)."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "CPU temperatures spike above 90°C on idle.",
                    fix = "Cooler bracket not fully seated or plastic peel left on base. Remove cooler, clean off old paste with 99% alcohol, check peel, reapply fresh thermal paste."
                )
            ),
            iconKey = "cpu"
        ),
        Lesson(
            id = "hw_ram",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "RAM Generations, Dual Channel & Reseating",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Random Access Memory (RAM) provides high-speed volatile storage for active code and data. Proper slot population enables dual-channel memory bandwidth.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Select Dual Channel Memory Slots",
                    description = "On standard 4-slot motherboards (labeled DIMM_A1, A2, B1, B2), install a 2-stick kit into slots 2 and 4 (A2 and B2) from the CPU socket.",
                    proTip = "Slots 2 and 4 provide the optimal signal trace termination on daisy-chain motherboards."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Reseating Technique",
                    description = "Press the side latches open. Align the offset notch on the RAM stick with the slot key. Push down firmly with two thumbs until the latches click shut automatically."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Enable XMP / EXPO in BIOS",
                    description = "By default RAM runs at base JEDEC speed. Enter BIOS and enable Extreme Memory Profile (XMP) or AMD EXPO to achieve the advertised speed (e.g. 3600MHz/6000MHz)."
                )
            ),
            requiredTools = listOf("Anti-static wrist strap", "Pencil eraser (for contact cleaning)"),
            importantWarnings = listOf(
                LessonWarning("DDR4 and DDR5 memory modules are physically notched differently and are NOT electrically or mechanically interchangeable.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Placing two sticks into adjacent slots 1 and 2, which runs memory in single-channel mode, halving memory bandwidth."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "PC powers on with black screen or motherboard DRAM debug LED lit.",
                    fix = "Reseat RAM sticks. Test with a single stick in Slot 2 (A2) to isolate whether one memory stick or slot is defective."
                )
            ),
            iconKey = "memory"
        ),
        Lesson(
            id = "hw_gpu",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "GPU Installation, PCIe Slots & Power Delivery",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Dedicated Graphics Processing Units (GPUs) provide high-performance rendering for 3D graphics, AI inference, and video encoding. Proper PCIe bandwidth and dedicated 8-pin power cables are critical.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Choose Primary PCIe Slot",
                    description = "Always install GPU into the topmost PCIe x16 slot directly wired to the CPU. Lower slots often route through the chipset with fewer PCIe lanes (x4 or x8)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Connect Dedicated PCIe Power Cables",
                    description = "Use separate, individual 8-pin (6+2) cables from the PSU for each connector. Avoid daisy-chain pigtail cables on high-draw GPUs (>220W).",
                    proTip = "On 12VHPWR / 12V-2x6 connectors (RTX 40-series), ensure the plug is pressed in with zero gap."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Secure Retention Bracket",
                    description = "Screw the metal bracket into the PC case chassis and support heavy cards with a GPU anti-sag bracket to prevent PCIe slot cracked solder joints."
                )
            ),
            requiredTools = listOf("Phillips #2 Screwdriver", "PCIe Power Cables", "Anti-sag bracket"),
            importantWarnings = listOf(
                LessonWarning("Never force release the PCIe retention latch without pressing down the plastic tab first, or you may snap the motherboard PCIe slot lock.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Plugging the monitor HDMI/DisplayPort cable into the motherboard I/O panel instead of the dedicated GPU ports."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Screen displays colorful artifacts, checkerboards, or green lines.",
                    fix = "VRAM solder degradation or failing GPU silicon. Test with another monitor/cable, re-seat GPU, underclock memory in MSI Afterburner, or RMA."
                )
            ),
            iconKey = "gpu"
        ),
        Lesson(
            id = "hw_motherboard",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "Motherboard Form Factors, Headers & VRMs",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 16,
            explanation = "The motherboard connects all subsystems. Understanding ATX/mATX/ITX form factors, front panel headers, and CMOS batteries ensures reliable assembly and troubleshooting.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Form Factor Dimensions",
                    description = "Standard ATX (305x244mm, 7 expansion slots), Micro-ATX (244x244mm, 4 slots), Mini-ITX (170x170mm, 1 slot). Ensure case standoffs match the board holes."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Wiring Front Panel Headers",
                    description = "Locate JFP1 / Front Panel header block: Power Switch (PWR_SW), Reset Switch (RESET_SW), Power LED (PWR_LED +/-), and HDD LED (HDD_LED +/-).",
                    proTip = "Power and Reset switches are momentary contacts and have no polarity. LEDs DO have polarity (+ and -)."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "CMOS Battery (CR2032)",
                    description = "The 3V lithium coin cell maintains BIOS clock and volatile NVRAM settings when AC power is disconnected.",
                    proTip = "A dead CMOS battery causes system time to reset to year 2020 on every boot."
                )
            ),
            requiredTools = listOf("Phillips Screwdriver", "CR2032 Battery", "Motherboard Manual Pinout"),
            importantWarnings = listOf(
                LessonWarning("Extra case standoff screws installed in locations without matching motherboard screw holes will cause a dead short against the PCB traces!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Forgetting to install the metal I/O shield plate before mounting the motherboard into the chassis."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Front power button does nothing when pressed.",
                    fix = "Bridge the two PWR_SW pins briefly with a flathead screwdriver. If system starts, the front case switch or wiring is defective."
                )
            ),
            iconKey = "chip"
        ),
        Lesson(
            id = "hw_psu",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "Power Supply Unit (PSU) & Paperclip Test",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "The PSU converts high-voltage AC wall power into regulated DC voltages (+12V, +5V, +3.3V, +5VSB) required by computer electronics.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "80 Plus Efficiency Ratings",
                    description = "80 Plus Bronze (82-85%), Silver, Gold (87-90%), Platinum, Titanium. Higher efficiency produces less waste heat and cleaner electrical output."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "The 24-Pin Paperclip Jump Test",
                    description = "Disconnect PSU cables from all motherboard and GPU components. Plug PSU into wall. On the 24-pin main harness, bridge Pin 16 (Green PS_ON wire) to any adjacent Black (Ground) pin with a metal paperclip. Switch on PSU.",
                    proTip = "If the PSU internal cooling fan spins, the power supply's primary standby rail and relay are functional."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Measuring Voltages with Digital Multimeter",
                    description = "Set DMM to DC Volts. Measure Yellow wires (+12V +/- 5%), Red wires (+5V +/- 5%), and Orange wires (+3.3V +/- 5%) against Black ground."
                )
            ),
            requiredTools = listOf("Digital Multimeter", "Metal Paperclip", "PSU Tester"),
            importantWarnings = listOf(
                LessonWarning("DANGER: NEVER open the metal casing of a power supply unit. High-voltage capacitors retain lethal charge (400V) even hours after being unplugged!", isDataDestructive = false),
                LessonWarning("Never mix modular cables from different PSU brands. Pinouts on the PSU side are not standardized and will destroy connected SSDs.", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Plugging an 8-pin PCIe cable into the 8-pin CPU EPS socket (different keying and reversed polarity)."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "PC turns on for half a second then immediately shuts off with a click.",
                    fix = "Short-circuit protection (SCP) triggered. Unplug all SATA drives, front panel USB, and GPU to find the component causing the short."
                )
            ),
            iconKey = "power"
        ),
        Lesson(
            id = "hw_hdd",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "Hard Disk Drive (HDD) S.M.A.R.T. & Failure Symptoms",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Mechanical HDDs store data magnetically on spinning platters (5400/7200 RPM) read by electromagnetic actuator arms. Wear and head crashes cause irreversible physical damage.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Reading S.M.A.R.T. Health Attributes",
                    description = "Use CrystalDiskInfo to inspect S.M.A.R.T. health. Critical failure indicators: 05 Reallocated Sectors Count, 0A Spin Retry Count, C5 Current Pending Sector Count.",
                    proTip = "Any raw value > 0 on Reallocated Sectors indicates physical media surface degradation."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Identifying the 'Click of Death'",
                    description = "A rhythmic clicking sound indicates the drive head is failing to locate servo tracks on the platter surface and repeatedly slamming against the home stopper."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Safe Handling Guidelines",
                    description = "Never move, bump, or rotate a computer while mechanical hard drives are actively spinning (risk of head crash gouging the magnetic surface)."
                )
            ),
            requiredTools = listOf("CrystalDiskInfo", "SATA Data Cable", "SATA to USB Enclosure"),
            importantWarnings = listOf(
                LessonWarning("If a drive is making rhythmic clicking or grinding noises, immediately power it off to prevent mechanical heads from scraping away the platter magnetic coating containing customer data!", isDataDestructive = true)
            ),
            commonMistakes = listOf(
                "Running intensive chkdsk /r on a failing physical HDD before cloning raw sectors to a backup drive."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Windows task manager shows 100% disk usage on HDD with 0 KB/s transfer rate.",
                    fix = "Drive has bad sectors and is locked in retry loops. Replace immediately with an SSD."
                )
            ),
            iconKey = "hdd"
        ),
        Lesson(
            id = "hw_ssd",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "2.5-Inch SATA SSD, AHCI Mode & TRIM",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Solid State Drives (SSDs) store data on non-volatile NAND flash memory chips. Replacing an old mechanical hard drive with a 2.5-inch SATA SSD provides a dramatic 5x to 10x real-world speed improvement.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "SATA 3 Connection & Speeds",
                    description = "SATA 3.0 provides up to 6 Gbps bandwidth (~550 MB/s real-world sequential read/write). Connect using SATA data cable to SATA 0/1 on motherboard and SATA power from PSU."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Verify AHCI Controller Mode in BIOS",
                    description = "Ensure SATA Controller is set to AHCI (not IDE or legacy). AHCI enables Native Command Queuing (NCQ) and hot-plugging.",
                    proTip = "IDE mode cuts SSD throughput in half."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Verify TRIM is Active in Windows",
                    description = "Open CMD as Admin and run: fsutil behavior query DisableDeleteNotify. If result is 0, TRIM is enabled. TRIM informs the SSD which data blocks can be cleared internally.",
                    commandOrKey = "fsutil behavior query DisableDeleteNotify"
                )
            ),
            requiredTools = listOf("2.5\" SATA SSD", "SATA Data Cable", "2.5\" to 3.5\" Mounting Bracket"),
            importantWarnings = listOf(
                LessonWarning("Never run disk defragmentation on an SSD. Defrag does not improve flash access times and causes unnecessary write wear cycles on NAND cells.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Filling an SSD beyond 90% capacity, which drastically degrades write performance due to lack of empty blocks for wear leveling."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "New SSD does not show up in 'This PC' File Explorer.",
                    fix = "Open Disk Management (diskmgmt.msc). The drive is unallocated and uninitialized. Initialize as GPT, right-click and create New Simple Volume."
                )
            ),
            iconKey = "storage"
        ),
        Lesson(
            id = "hw_nvme",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "NVMe M.2 SSDs (PCIe Gen 3, 4 & 5)",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "NVMe (Non-Volatile Memory Express) SSDs communicate directly over PCIe lanes at bandwidths up to 3500MB/s (Gen 3), 7000MB/s (Gen 4), and 14000MB/s (Gen 5).",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Form Factors & Keying",
                    description = "Most common is M.2 2280 (22mm wide, 80mm long) with 'M-Key' notch. SATA M.2 drives use 'B+M key' and are limited to 550MB/s."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Installation Standoff & Screw",
                    description = "Insert M.2 stick at a 30-degree angle into the slot. Push down gently and secure with the small M2 screw or rotating plastic latch.",
                    proTip = "Always ensure the brass standoff underneath is installed at the 2280 hole position."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Thermal Pad & Heatsink",
                    description = "Gen 4 and Gen 5 NVMe drives run hot (controller can exceed 80°C). Peel the plastic film off the motherboard thermal pad before clamping the metal heatsink."
                )
            ),
            requiredTools = listOf("Precision #0 Phillips Screwdriver", "M2 Standoff & Screw"),
            importantWarnings = listOf(
                LessonWarning("Do not overtighten the M2 screw, which can bend or crack the delicate multi-layer PCB of the NVMe drive.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving the blue/clear protective film on the motherboard NVMe thermal pad, acting as a thermal insulator."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "NVMe drive not detected in BIOS after installation.",
                    fix = "Some M.2 slots share lanes with SATA ports 5/6 or secondary PCIe slots. Check motherboard manual for lane bifurcation rules or update BIOS."
                )
            ),
            iconKey = "speed"
        ),
        Lesson(
            id = "hw_assembly",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "Complete Desktop PC Assembly Guide",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 25,
            explanation = "Systematic PC assembly order minimizes clearance issues, avoids motherboard trace scratches, and ensures first-time POST success.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Core Component Assembly Outside the Case",
                    description = "Place motherboard on top of its cardboard box. Install: 1) CPU into socket, 2) NVMe SSD into M.2_1 slot, 3) RAM into slots A2 and B2, 4) CPU cooler mounting brackets.",
                    proTip = "Installing these components outside the case provides 100% unobstructed hand clearance."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Chassis Preparation",
                    description = "Install rear I/O shield (if not integrated). Verify standoffs match motherboard screw layout. Install PSU into bottom basement and route CPU 8-pin and 24-pin cables."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Mount Motherboard & Cable Connections",
                    description = "Lower motherboard onto standoffs. Screw in center screw first. Connect 24-pin ATX, 8-pin CPU EPS, Front Panel I/O, USB 3.0 header, and HD Audio."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Install Dedicated GPU & Final Wiring",
                    description = "Remove case PCIe expansion slot covers. Insert GPU until latch clicks. Screw in bracket, connect PCIe power cables, and tidy cables with zip ties."
                )
            ),
            requiredTools = listOf("Magnetic #2 Phillips Screwdriver", "Cable Ties", "Diagonal Cutters", "Anti-static Mat"),
            importantWarnings = listOf(
                LessonWarning("Never assemble a PC directly on carpet; static electrical discharge (ESD) can destroy MOS-FET transistors.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Plugging the front panel USB 3.0 header with excessive angle, bending the delicate internal pins."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Assembly complete, but power button does nothing.",
                    fix = "Verify PSU rear master power switch is in the 'I' (On) position, wall outlet has power, and front panel PWR_SW leads are connected to the correct pins."
                )
            ),
            iconKey = "pc"
        ),
        Lesson(
            id = "hw_diagnostics",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "Hardware Diagnostics & POST Debug LEDs",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "Power-On Self-Test (POST) executes when a computer is turned on. Motherboard Debug LEDs (CPU, DRAM, VGA, BOOT) isolate exactly which component is failing initialization.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Reading 4-Stage Debug LEDs",
                    description = "Watch the 4 LEDs during boot: 1) CPU (tests processor initialization), 2) DRAM (tests memory communication), 3) VGA (detects GPU), 4) BOOT (seeks bootable drive).",
                    proTip = "The light that STAYS illuminated indicates the subsystem failing POST."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Interpreting Motherboard Beep Codes",
                    description = "Single beep: POST OK. 1 long 2/3 short beeps: Video/GPU fault. Continuous rapid beeps: RAM or power fault. High/low alternating siren: CPU thermal trip."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Barebones Bench Testing (Breadboarding)",
                    description = "Disconnect all peripherals, external drives, front USB headers, and audio cables. Test with only CPU, 1 stick of RAM, PSU, and display."
                )
            ),
            requiredTools = listOf("Motherboard Piezo Buzzer / Speaker", "Digital Multimeter"),
            importantWarnings = listOf(
                LessonWarning("Always power off and disconnect AC wall power before adding, removing, or swapping components during diagnostic isolation.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Assuming a motherboard is dead without checking if the DRAM LED is simply halted on memory training."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "DRAM LED stays on for 2-3 minutes on new AMD AM5 / Intel DDR5 builds.",
                    fix = "This is normal DDR5 memory training on first boot. Wait at least 5 minutes before interrupting power."
                )
            ),
            iconKey = "diagnostics"
        ),
        Lesson(
            id = "hw_no_power",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "No Power Troubleshooting (Dead PC)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "When a PC exhibits zero signs of life (no fans, no LEDs, no sounds), technicians follow systematic electrical elimination from wall socket to motherboard rails.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Check External Power Source",
                    description = "Test wall socket with a working lamp. Verify AC power cord is seated deeply into PSU and PSU rocker switch is set to 'I' (On)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Inspect Motherboard Standby LED",
                    description = "Look for a tiny illuminated 5VSB LED on the motherboard. If illuminated, +5V standby power is reaching the motherboard."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Bypass Front Power Button",
                    description = "Case switches fail frequently. Unplug the front panel connector and momentarily touch the two motherboard PWR_SW pins with a metal screwdriver tip."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Isolate Short Circuit",
                    description = "Unplug all USB peripherals, front panel USB 3.0 cables, SATA power cables, and GPU. A damaged front USB port with bent touching pins will ground out the 5V rail and lock the PSU in safety shutdown."
                )
            ),
            requiredTools = listOf("Known-good AC Power Cord", "Flathead Screwdriver", "Digital Multimeter"),
            importantWarnings = listOf(
                LessonWarning("Never touch motherboard pins with conductive tools while wearing metal rings, bracelets, or watches.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Replacing the motherboard before checking if the case power switch button plastic plunger is physically snapped inside the bezel."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "PC clicks and fan twitches once, then dead silence.",
                    fix = "Short circuit detected on +12V rail. Unplug the 8-pin CPU power cable. If fans now spin, the motherboard VRM MOSFETs are shorted."
                )
            ),
            iconKey = "power"
        ),
        Lesson(
            id = "hw_no_display",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "No Display / Black Screen Troubleshooting",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 15,
            explanation = "The PC powers on, case fans spin, RGB lights glow, but the monitor displays 'No Signal' or remains black. This is one of the most common IT repair service calls.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Verify Cable Connection & Monitor Input Source",
                    description = "Ensure video cable (HDMI/DP) is plugged into the DEDICATED GPU (horizontal ports near bottom), NOT the motherboard vertical ports. Verify monitor input is set to HDMI 1 or DP."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "RAM Reseat & Contact Cleaning",
                    description = "Over 70% of no-display issues are caused by oxidized or improperly seated RAM contacts. Remove sticks, clean gold fingers with a pencil eraser, blow out dust, and reseat into slot 2."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Perform Full CMOS Reset",
                    description = "Unplug AC power cord. Remove CR2032 coin battery. Press and hold chassis power button for 30 seconds to drain capacitors. Reinsert battery and turn on.",
                    proTip = "CMOS reset clears bad memory timings or corrupted overclock profiles."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Test with Integrated Graphics",
                    description = "If CPU has an iGPU, remove the dedicated GPU completely and plug HDMI directly into the motherboard to rule out a dead graphics card."
                )
            ),
            requiredTools = listOf("Pencil Eraser", "Spare HDMI / DP Cable", "CR2032 Battery"),
            importantWarnings = listOf(
                LessonWarning("Ensure monitor power brick is genuine. A failing monitor power adapter will power the monitor LED but fail to light the backlight panel.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Connecting display cable to motherboard on CPUs that have no integrated graphics (e.g., Intel 'F' series like i5-13400F, or AMD Ryzen non-G processors)."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Monitor power LED blinks amber, screen stays black.",
                    fix = "Cable or resolution handshake failure. Try a DisplayPort cable instead of HDMI or test with a secondary monitor."
                )
            ),
            iconKey = "display"
        ),
        Lesson(
            id = "hw_overheating",
            categoryId = "pc_hardware",
            categoryTitle = "PC Hardware",
            title = "Overheating & Thermal Throttling Troubleshooting",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "CPUs and GPUs throttle clock frequencies when temperatures exceed 90°C-100°C to prevent thermal destruction. Symptoms include frame rate stuttering and sudden shutdowns during load.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Monitor Real-Time Temperatures with HWMonitor / Core Temp",
                    description = "Install HWMonitor or Core Temp. Check idle temps (should be 35°C - 50°C) and load temps under Cinebench (should not exceed 85°C - 90°C)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Inspect Dust Buildup & Fan Operation",
                    description = "Clean radiator fins and heatsink stacks with compressed air. Ensure all intake and exhaust case fans are spinning."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Diagnose AIO Liquid Cooler Pump Failure",
                    description = "If using liquid cooling: feel the two rubber hoses while system runs. One should be warm, one slightly cooler. Feel the pump block for vibration. If CPU hits 100°C within 60 seconds of boot and hoses are cold, the pump is dead or airlocked.",
                    proTip = "Radiator must be mounted higher than the pump block to prevent air bubbles settling in the impeller."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Repasting the Heatsink",
                    description = "Remove cooler. Wipe away dry, crusty thermal paste with 99% isopropyl alcohol until mirror shine is visible. Apply fresh compound."
                )
            ),
            requiredTools = listOf("HWMonitor / Core Temp", "Can of Compressed Air", "Thermal Paste", "99% Isopropyl Alcohol"),
            importantWarnings = listOf(
                LessonWarning("When blowing compressed air onto cooling fans, hold the fan blades with a finger so they do not overspin, which acts as a generator and sends reverse voltage into the motherboard fan header.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Mounting AIO liquid cooler radiator at the very bottom of the case below the pump, guaranteeing pump cavitation and premature failure."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Laptop fan screams at full speed, chassis is scorching hot.",
                    fix = "Remove laptop bottom cover. Clean lint carpet caught between fan blower and copper heatsink exhaust fins. Repaste CPU/GPU with high-viscosity paste."
                )
            ),
            iconKey = "temperature"
        )
    )
}
