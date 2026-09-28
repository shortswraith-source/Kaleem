package com.example.data.datasource

import com.example.data.model.DifficultyLevel
import com.example.data.model.Lesson
import com.example.data.model.LessonStep
import com.example.data.model.LessonWarning
import com.example.data.model.TroubleshootingItem

object CctvLessonsDataSource {
    fun getLessons(): List<Lesson> = listOf(
        Lesson(
            id = "cctv_dvr_basics",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "DVR vs NVR Fundamentals & Architectures",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "Video surveillance systems utilize either Digital Video Recorders (DVR) with analog coaxial cabling or Network Video Recorders (NVR) with IP Ethernet cabling and PoE switches.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "DVR System Architecture",
                    description = "DVRs receive raw analog electrical signals (HD-TVI, CVI, AHD, CVBS) through RG59 coaxial cables and BNC twist connectors. Video encoding/digitization occurs INSIDE the DVR processor."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "NVR System Architecture",
                    description = "NVRs communicate over digital IP networks (Cat5e/Cat6 RJ45). Video digitization and H.264/H.265 compression occurs INSIDE the camera chip. The NVR merely stores data streams."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Channel Capacities & Resolution Matching",
                    description = "Common configurations: 4CH, 8CH, 16CH, 32CH. Always check the recorder's incoming decoding bandwidth (Mbps) and max MP resolution (2MP/1080p, 4MP, 4K/8MP)."
                )
            ),
            requiredTools = listOf("BNC Connectors", "RG59 Coaxial Cable", "RJ45 Ethernet Cable", "HDMI/VGA Test Monitor"),
            importantWarnings = listOf(
                LessonWarning("Mismatching analog signal technologies (e.g., plugging an 8MP TVI camera into an older 2MP AHD DVR) results in 'Unsupported Resolution' or rolling black screen.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Running high-voltage 120V/240V AC power parallel alongside unshielded analog CCTV coaxial lines, inducing severe 60Hz hum ground loop bars across the video image."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Black screen with rolling horizontal dark bars on analog camera.",
                    fix = "Ground loop problem. Install a BNC Ground Loop Isolator filter inline at the DVR input."
                )
            ),
            iconKey = "camera"
        ),
        Lesson(
            id = "cctv_hdd_install",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Surveillance HDD Installation & Formatting",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 15,
            explanation = "Surveillance hard drives operate 24/7/365 with 90% write cycles. Using a desktop PC drive instead of a surveillance-grade drive (WD Purple, Seagate SkyHawk) causes early mechanical head failure.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Select Surveillance-Grade Drive",
                    description = "Choose Western Digital Purple or Seagate SkyHawk. These feature specialized AllFrame firmware that prioritizes continuous write streaming without frame dropping."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Mount Hard Drive Inside DVR Chassis",
                    description = "Disconnect power from DVR. Open top metal cover screws. Place 3.5\" HDD on rubber anti-vibration grommets and secure with 4 bottom chassis screws.",
                    proTip = "Vibration dampening rubber washers prevent harmonic chassis vibration."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Connect SATA Data & Power",
                    description = "Plug the compact SATA data cable and 4-pin to SATA power cable securely into the DVR motherboard and drive."
                ),
                LessonStep(
                    stepNumber = 4,
                    title = "Format / Initialize HDD in DVR GUI",
                    description = "Power on DVR -> Storage Management / HDD Management -> select new drive -> click 'Init' or 'Format'. Status must change from 'Unformatted' to 'Normal' (R/W).",
                    commandOrKey = "Init / Format HDD"
                )
            ),
            requiredTools = listOf("Phillips #2 Screwdriver", "Surveillance HDD (WD Purple / Seagate SkyHawk)"),
            importantWarnings = listOf(
                LessonWarning("DATA LOSS WARNING: Initializing/formatting the HDD in the DVR erases all existing recordings and proprietary file structures.", isDataDestructive = true),
                LessonWarning("Do not use standard desktop drives (WD Blue / Seagate Barracuda); they are rated for 8x5 office duty and will fail within 6-12 months under 24/7 camera write loads.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Closing the DVR lid without formatting the hard drive in the DVR menu, leaving the unit unable to record any footage."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "DVR beeps continuously after startup, HDD status: 'Not Formatted' or 'Error'.",
                    fix = "Enter Storage menu, select the drive checkbox, and click Format/Init. If format fails at 0%, check SATA power connector."
                )
            ),
            iconKey = "hdd"
        ),
        Lesson(
            id = "cctv_sata_troubleshooting",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "SATA Power & Voltage Drop Troubleshooting",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 14,
            explanation = "Over 60% of 'DVR HDD Not Detected' issues are caused by degraded 12V DC power adapters that can power the motherboard but cannot deliver the 2A spin-up surge required by hard drives.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Inspect DVR Power Adapter Amp Rating",
                    description = "Check the small external 12V power brick. Standard 4CH/8CH DVRs require at least 12V 2A (or 12V 3A for 2 drives). Aging capacitors cause voltage to sag to 10.2V under load."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Listen for HDD Spin-Up",
                    description = "Place hand on hard drive during DVR boot. You should feel smooth rotational vibration and hear a single soft click as heads calibrate."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Swap SATA Cable & Port",
                    description = "Thin DVR SATA cables bend easily and develop internal trace breaks. Replace with a new SATA cable and test alternate SATA port on board."
                )
            ),
            requiredTools = listOf("Digital Multimeter", "Known-Good 12V 2A / 3A Power Adapter", "Replacement SATA Cable"),
            importantWarnings = listOf(
                LessonWarning("Never use a 19V or 24V laptop charger on a 12V DVR; doing so will immediately blow the motherboard overvoltage TVS protection diodes!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Assuming the hard drive is bad when the real culprit is a dried-out 12V 1.5A external power adapter."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "HDD clicks repeatedly every 3 seconds and DVR reboots.",
                    fix = "Classic voltage drop symptom during drive spin-up. Replace the 12V power brick with a 12V 3A or 12V 5A regulated adapter."
                )
            ),
            iconKey = "power"
        ),
        Lesson(
            id = "cctv_camera_connection",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Analog BNC Cabling, Video Baluns & 12V Power",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "Connecting analog CCTV cameras involves RG59 Siamese cable (coax + 18/2 power) or UTP Cat5e/Cat6 cable with passive video baluns and central 12V DC power distribution boxes.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "BNC Compression vs Twist-On Connectors",
                    description = "Always use professional BNC compression connectors crimped with a ratcheting tool. Twist-on BNCs become loose and cause video flicker over time."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Video Baluns over Cat5e/Cat6 UTP Cable",
                    description = "Passive video baluns convert unbalanced 75-ohm coaxial signals to balanced 100-ohm UTP signals, allowing 4 camera video streams over a single 4-pair network cable up to 250 meters."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "12V DC Central Power Supply Box",
                    description = "Wire camera power to a multi-channel 12V DC power supply box with PTC auto-reset fuses (e.g., 9-channel 10A box). Observe correct Red (+) and Black (-) polarity.",
                    proTip = "Reversing DC polarity will fry camera mainboards."
                )
            ),
            requiredTools = listOf("BNC Compression Crimper", "Coax Wire Stripper", "Digital Multimeter", "Small Terminal Screwdriver"),
            importantWarnings = listOf(
                LessonWarning("Verify camera voltage specification (12V DC vs 24V AC). Connecting 24V AC to a 12V DC camera causes instant component burnout!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Exceeding distance limits without calculating DC voltage drop. Over long runs (>50m), 12V drops to 9V, causing IR night-vision LEDs to flicker and drop video."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Camera works fine during daytime but goes black at night.",
                    fix = "When IR cut filter and night-vision LEDs turn on, current draw triples. Voltage drops below minimum threshold. Increase wire gauge or move power supply closer."
                )
            ),
            iconKey = "camera"
        ),
        Lesson(
            id = "cctv_ip_cameras",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "IP Cameras, PoE Standards (802.3af/at) & Subnets",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 16,
            explanation = "IP cameras transmit digital video packets over Ethernet. Power over Ethernet (PoE) injects 48V DC power directly through network cable pairs, eliminating separate camera power cables.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "PoE Standards Overview",
                    description = "Standard PoE (802.3af, 15.4W): Ideal for fixed bullet and dome cameras. PoE+ (802.3at, 30W): Required for PTZ motorized zoom cameras and cameras with built-in heater blowers."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Direct NVR PoE Ports vs External PoE Switch",
                    description = "NVR built-in PoE ports operate on an isolated internal subnet (e.g. 192.168.254.x). External PoE switches share the main LAN subnet (e.g. 192.168.1.x)."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Default IP Subnet Discovery & SADP Tool",
                    description = "Brand new IP cameras ship with factory default static IPs (Hikvision: 192.168.1.64, Dahua: 192.168.1.108). Use manufacturer utility (Hikvision SADP / Dahua ConfigTool) to discover and activate."
                )
            ),
            requiredTools = listOf("PoE Switch / NVR with PoE", "Cat6 Cable", "Laptop with SADP / ConfigTool"),
            importantWarnings = listOf(
                LessonWarning("Never connect an active 48V PoE switch port directly into a standard non-PoE network port on older test devices or laptops unless the switch is 802.3af active handshake compliant.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving multiple new cameras unassigned with duplicate default IP 192.168.1.64, causing IP conflict loops and blinking video channels."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "NVR shows IP Camera status 'Offline (Account Locked)'.",
                    fix = "Hikvision/Dahua cameras lock after 5 bad password attempts. Connect laptop, unlock with SADP tool, and match NVR channel password with camera admin password."
                )
            ),
            iconKey = "network"
        ),
        Lesson(
            id = "cctv_network_config",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Recorder Network Configuration & Port Management",
            difficulty = DifficultyLevel.INTERMEDIATE,
            estimatedTimeMinutes = 15,
            explanation = "Configuring static IP settings, DNS, and server ports ensures smooth local streaming, prevents IP conflicts, and allows seamless mobile app connectivity.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Set Static IP Address",
                    description = "DVR/NVR Network -> TCP/IP -> Disable DHCP. Assign Static IP (e.g., 192.168.1.250). Set Subnet Mask to 255.255.255.0 and Default Gateway to router IP (192.168.1.1)."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Configure Reliable DNS Servers",
                    description = "Set Preferred DNS to 8.8.8.8 (Google) and Alternate DNS to 1.1.1.1 (Cloudflare) so the DVR can resolve P2P cloud relay servers."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Key Surveillance Ports",
                    description = "HTTP Port (default 80, change to 8080 for security), Server/Media Port (Hikvision: 8000, Dahua: 37777), RTSP Video Stream Port (default 554)."
                )
            ),
            requiredTools = listOf("DVR Control Menu", "Mouse", "Local Router IP Info"),
            importantWarnings = listOf(
                LessonWarning("Never place a DVR in the router's DMZ (Demilitarized Zone). Automated IoT botnets (Mirai) actively scan and exploit unpatched DVR ports within minutes!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Leaving gateway or DNS blank, which prevents P2P cloud status from reaching 'Online'."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "DVR Cloud P2P status says 'Offline' or 'Not Connected'.",
                    fix = "Check DNS servers. Change DNS 1 to 8.8.8.8 and DNS 2 to 8.8.4.4. Verify router has internet access."
                )
            ),
            iconKey = "settings"
        ),
        Lesson(
            id = "cctv_recording_setup",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Recording Schedules, Motion Detection & Codecs",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "Optimizing recording parameters balances hard drive storage capacity against video quality. Utilizing H.265+ compression and motion-triggered recording can quadruple retention days.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Continuous 24/7 vs Motion Detection",
                    description = "Continuous (Normal): Records non-stop. Motion Detection (MD): Only writes video when pixels change. Hybrid best practice: Continuous on exterior entrances, Motion-only on interior hallways."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Video Compression Codecs (H.264 vs H.265 / H.265+)",
                    description = "H.265 (HEVC) cuts bandwidth and storage in half compared to legacy H.264 without loss of visual quality. Smart H.265+ (Hikvision) or Smart Codec (Dahua) compresses static backgrounds even further."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Setting Motion Detection Grid & Sensitivity",
                    description = "In Camera Event -> Motion: Mask out tree branches, waving bushes, and busy public roads to avoid false motion triggers filling up storage."
                )
            ),
            requiredTools = listOf("DVR Recording Schedule Menu"),
            importantWarnings = listOf(
                LessonWarning("Older web browsers and mobile devices may not support hardware decoding of H.265+; test playback on client smartphones after enabling.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Setting motion sensitivity to 100%, causing flying insects and rain to trigger continuous recording."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "DVR runs out of storage space in 4 days with a 4TB HDD.",
                    fix = "Bitrate set too high or H.264 used on 4K cameras. Switch codec to H.265, enable VBR (Variable Bit Rate), and lower FPS from 30fps to 15fps."
                )
            ),
            iconKey = "record"
        ),
        Lesson(
            id = "cctv_playback_export",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Playback Investigation, Timeline & USB Video Export",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "Retrieving incident video footage quickly and exporting it in tamper-evident formats for law enforcement or customer review.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Timeline Navigation & Color Coding",
                    description = "In Playback menu: Select date on calendar. Look at bottom color-coded timeline bar: Blue = Continuous 24/7, Green/Yellow = Motion Event, Red = Alarm Input."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Clipping Video Snippet",
                    description = "Click the 'Scissors' / 'Clipping' icon at incident start time. Scrub forward to incident end time, click Scissors again to set the export segment."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Export to USB Flash Drive",
                    description = "Insert FAT32 formatted USB drive. Choose export format: MP4 (standard playback) or native AVI/DAV with proprietary player for digital watermark verification."
                )
            ),
            requiredTools = listOf("FAT32 Formatted USB Flash Drive (16GB/32GB)", "VLC Media Player"),
            importantWarnings = listOf(
                LessonWarning("Do not use NTFS or exFAT formatted flash drives on budget DVRs; most embedded Linux DVR kernels only recognize FAT32 partitions.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Exporting footage without the native player executable, leaving customer unable to play proprietary H.265 .dav files on standard Windows Media Player."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Exported CCTV video file will not play on client computer.",
                    fix = "Install VLC Media Player or download the official manufacturer playback software (Hikvision VSPlayer or Dahua Smart Player)."
                )
            ),
            iconKey = "play"
        ),
        Lesson(
            id = "cctv_remote_viewing",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Remote Viewing Setup (P2P Cloud vs Port Forwarding)",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 14,
            explanation = "Enabling live camera feeds on iOS/Android smartphones using secure Peer-to-Peer (P2P) Cloud services (Hik-Connect, DMSS, Guarding Vision) without risky router port forwarding.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Why P2P Cloud is Preferred Over Port Forwarding",
                    description = "P2P connects outward through an encrypted reverse tunnel to manufacturer AWS/Azure relays. It bypasses ISP CGNAT, requires zero open router ports, and handles dynamic public IPs automatically."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Enable P2P / Cloud Service on DVR",
                    description = "Network -> Advanced -> Platform Access. Check 'Enable'. Create a custom Device Verification Code / Encryption Key."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Scan QR Code in Mobile App",
                    description = "Download Hik-Connect, DMSS, or manufacturer app. Tap '+' -> Scan QR Code displayed on DVR monitor. Enter verification code to link device."
                )
            ),
            requiredTools = listOf("Smartphone with Mobile Data", "DVR Connected to Internet"),
            importantWarnings = listOf(
                LessonWarning("Always write down the verification code / stream encryption password; without it, video streams appear scrambled and cannot be viewed on new phones.", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Testing Wi-Fi remote viewing on phone while connected to the same office Wi-Fi network, masking external cellular connectivity issues."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "Mobile app says 'Device is Bound to Another Account'.",
                    fix = "Previous owner or installer linked the serial number. Click 'Unbind Device' in app while connected to the same local Wi-Fi, entering DVR admin credentials to verify ownership."
                )
            ),
            iconKey = "phone"
        ),
        Lesson(
            id = "cctv_errors_beeping",
            categoryId = "cctv_dvr",
            categoryTitle = "CCTV & DVR",
            title = "Common DVR Errors, Beeping Codes & Diagnostics",
            difficulty = DifficultyLevel.BEGINNER,
            estimatedTimeMinutes = 12,
            explanation = "DVRs emit piercing audible beeps to alert owners of security or hardware failures. Silencing the buzzer and identifying the root exception restores system reliability.",
            stepByStep = listOf(
                LessonStep(
                    stepNumber = 1,
                    title = "Identify the Exception Trigger",
                    description = "Go to System -> Exception / Event. Check the Exception Type dropdown: HDD Full, HDD Error, Network Disconnected, IP Address Conflicted, Illegal Login, Video Loss."
                ),
                LessonStep(
                    stepNumber = 2,
                    title = "Temporarily Mute Buzzer",
                    description = "In Exception Settings, uncheck 'Audible Warning' or 'Buzzer' for the specific event while performing repairs."
                ),
                LessonStep(
                    stepNumber = 3,
                    title = "Top Causes of Annoying DVR Beeps",
                    description = "1) Hard drive unformatted or bad sectors (80% of cases). 2) Network cable unplugged. 3) Video signal loss on an enabled channel with no camera plugged in."
                )
            ),
            requiredTools = listOf("DVR USB Mouse", "Replacement HDD / Network Cable"),
            importantWarnings = listOf(
                LessonWarning("Do not permanently leave 'HDD Error' buzzer muted without replacing the drive; customer will discover zero recordings exist after a burglary!", isDataDestructive = false)
            ),
            commonMistakes = listOf(
                "Having 'HDD Full' alarm enabled when hard drive is already configured to automatically overwrite oldest recordings."
            ),
            troubleshootingTips = listOf(
                TroubleshootingItem(
                    symptom = "DVR beeps every 10 seconds incessantly.",
                    fix = "Look at Storage menu. If HDD status shows 'Abnormal' or 'S.M.A.R.T. Bad', replace hard drive."
                )
            ),
            iconKey = "alert"
        )
    )
}
