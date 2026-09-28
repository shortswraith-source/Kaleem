package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.QuizQuestion

object QuizDataSource {
    fun getQuestions(difficulty: DifficultyLevel): List<QuizQuestion> {
        return when (difficulty) {
            DifficultyLevel.BEGINNER -> getBeginnerQuestions()
            DifficultyLevel.INTERMEDIATE -> getIntermediateQuestions()
            DifficultyLevel.ADVANCED -> getAdvancedQuestions()
        }
    }

    private fun getBeginnerQuestions(): List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q_b1",
            question = "Which color wire pinout is Pin 1 in the worldwide T568B RJ45 wiring standard?",
            options = listOf("Green", "White-Orange", "White-Green", "Blue"),
            correctIndex = 1,
            explanation = "T568B pinout begins with Pin 1: White-Orange, followed by Pin 2: Orange, Pin 3: White-Green.",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q_b2",
            question = "When assembling a PC, what critical mistake with the CPU heatsink leads to instant 100°C thermal throttling?",
            options = listOf(
                "Using non-conductive paste",
                "Forgetting to peel the transparent protective plastic film off the heatsink base",
                "Using a magnetic screwdriver",
                "Installing RAM in slot 2"
            ),
            correctIndex = 1,
            explanation = "Leaving the factory clear protective plastic sticker on the copper base of the cooler acts as a thermal insulator, preventing heat from transferring to the heatsink.",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q_b3",
            question = "What does an IP address starting with '169.254.x.x' signify on a Windows computer?",
            options = listOf(
                "Super-fast fiber optic link",
                "APIPA assignment indicating DHCP server request failed",
                "Public static server IP",
                "Local loopback diagnostic adapter"
            ),
            correctIndex = 1,
            explanation = "Automatic Private IP Addressing (APIPA) in the 169.254.0.0/16 range is automatically assigned by Windows when a DHCP server cannot be reached.",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q_b4",
            question = "Which keyboard shortcut directly opens Task Manager in Windows without showing the security screen?",
            options = listOf("Ctrl + Alt + Del", "Ctrl + Shift + Esc", "Win + Tab", "Alt + F4"),
            correctIndex = 1,
            explanation = "Ctrl + Shift + Esc directly opens the Windows Task Manager instantly.",
            difficulty = DifficultyLevel.BEGINNER
        ),
        QuizQuestion(
            id = "q_b5",
            question = "Why should you NEVER vacuum spilled laser printer toner with a normal household vacuum cleaner?",
            options = listOf(
                "It makes too much noise",
                "Fine toner dust passes through standard filters and can cause a static dust explosion in the motor",
                "Toner melts the plastic hose",
                "It voids the vacuum warranty"
            ),
            correctIndex = 1,
            explanation = "Toner particles are microscopic plastic and carbon dust that generate static electricity as they rush through plastic hoses, which can ignite and cause an electrical dust explosion.",
            difficulty = DifficultyLevel.BEGINNER
        )
    )

    private fun getIntermediateQuestions(): List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q_i1",
            question = "Which Windows command repairs corrupted system files by pulling clean copies from the online component store?",
            options = listOf(
                "chkdsk /f",
                "DISM /Online /Cleanup-Image /RestoreHealth",
                "ipconfig /flushdns",
                "format C: /q"
            ),
            correctIndex = 1,
            explanation = "DISM /Online /Cleanup-Image /RestoreHealth repairs the underlying Windows Component Store using Windows Update servers before SFC is run.",
            difficulty = DifficultyLevel.INTERMEDIATE
        ),
        QuizQuestion(
            id = "q_i2",
            question = "What is the primary cause of a security DVR suddenly beeping and reporting 'HDD Not Detected'?",
            options = listOf(
                "Camera lenses need cleaning",
                "Degraded 12V DC power adapter unable to deliver HDD spin-up surge current",
                "BNC cable impedance mismatch",
                "H.265 codec corruption"
            ),
            correctIndex = 1,
            explanation = "External 12V power adapters degrade over time; voltage sags below 11.4V under drive motor spin-up, preventing the hard drive from initializing.",
            difficulty = DifficultyLevel.INTERMEDIATE
        ),
        QuizQuestion(
            id = "q_i3",
            question = "In an IPv4 network with subnet mask 255.255.255.0 (/24), how many usable host IP addresses are available?",
            options = listOf("256", "254", "128", "512"),
            correctIndex = 1,
            explanation = "A /24 network has 256 total addresses (2^8), minus 2 reserved addresses (Network ID .0 and Broadcast .255), leaving 254 usable host addresses.",
            difficulty = DifficultyLevel.INTERMEDIATE
        ),
        QuizQuestion(
            id = "q_i4",
            question = "What is the maximum certified distance for a standard horizontal Category 6 Ethernet cable run?",
            options = listOf("50 meters", "100 meters", "250 meters", "500 meters"),
            correctIndex = 1,
            explanation = "The IEEE 802.3 standard specifies a maximum channel length of 100 meters (328 feet) for twisted pair Ethernet.",
            difficulty = DifficultyLevel.INTERMEDIATE
        ),
        QuizQuestion(
            id = "q_i5",
            question = "Why does disabling 'SNMP Status Enabled' in printer port properties often fix phantom 'Printer Offline' errors?",
            options = listOf(
                "It increases print speed by 50%",
                "It prevents Windows from marking the printer offline when minor network latency delays SNMP ping responses",
                "It disables toner notifications",
                "It converts PCL6 to PostScript"
            ),
            correctIndex = 1,
            explanation = "When SNMP Status is active, any packet loss or blocked SNMP port 161 causes Windows to falsely report the printer as offline even though raw port 9100 is open.",
            difficulty = DifficultyLevel.INTERMEDIATE
        )
    )

    private fun getAdvancedQuestions(): List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q_a1",
            question = "A computer crashes with BSOD bugcheck WHEA_UNCORRECTABLE_ERROR. What is the root cause category?",
            options = listOf(
                "Hardware failure (CPU core voltage, failing NVMe controller, or unstable PCIe bus)",
                "Corrupted font cache",
                "Printer spooler crash",
                "Missing Windows license key"
            ),
            correctIndex = 0,
            explanation = "WHEA (Windows Hardware Error Architecture) indicates a genuine physical hardware exception raised directly by the CPU or motherboard architecture.",
            difficulty = DifficultyLevel.ADVANCED
        ),
        QuizQuestion(
            id = "q_a2",
            question = "When rebuilding UEFI boot files from WinRE Command Prompt, which command properly installs fresh EFI boot code?",
            options = listOf(
                "bootrec /fixmbr",
                "bcdboot D:\\Windows /s S: /f UEFI",
                "format C: /fs:ntfs",
                "sfc /scannow /offbootdir"
            ),
            correctIndex = 1,
            explanation = "bcdboot D:\\Windows /s S: /f UEFI copies essential bootloader files from the Windows directory to the mounted EFI System Partition (S:).",
            difficulty = DifficultyLevel.ADVANCED
        ),
        QuizQuestion(
            id = "q_a3",
            question = "Why must surveillance systems use WD Purple or Seagate SkyHawk drives instead of standard desktop drives (WD Blue / Barracuda)?",
            options = listOf(
                "They have higher RPM speeds",
                "They feature specialized streaming firmware (like AllFrame) optimized for 24/7 continuous write cycles without dropping video frames",
                "They use USB connections",
                "They are immune to power outages"
            ),
            correctIndex = 1,
            explanation = "Surveillance drives prioritize continuous ATA streaming write commands over data integrity read retries, preventing dropped video frames in multi-camera feeds.",
            difficulty = DifficultyLevel.ADVANCED
        ),
        QuizQuestion(
            id = "q_a4",
            question = "What does S.M.A.R.T. Attribute 05 'Reallocated Sectors Count' signify when its raw value is greater than 0?",
            options = listOf(
                "The drive is brand new",
                "Physical magnetic surface damage has caused the drive controller to remap bad sectors to spare reserve sectors",
                "Windows defrag is needed",
                "The drive is encrypted"
            ),
            correctIndex = 1,
            explanation = "A non-zero Reallocated Sectors Count proves physical media defects; the drive is actively degrading and should be backed up and replaced immediately.",
            difficulty = DifficultyLevel.ADVANCED
        ),
        QuizQuestion(
            id = "q_a5",
            question = "What is the IEEE standard specification for PoE+ delivering up to 30 Watts of power per port for PTZ cameras?",
            options = listOf("IEEE 802.11ax", "IEEE 802.3af", "IEEE 802.3at", "IEEE 802.1Q"),
            correctIndex = 2,
            explanation = "IEEE 802.3at is PoE+ (delivering up to 30W), while 802.3af is standard PoE (up to 15.4W).",
            difficulty = DifficultyLevel.ADVANCED
        )
    )
}
