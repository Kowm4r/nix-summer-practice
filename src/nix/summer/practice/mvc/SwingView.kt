package nix.summer.practice.mvc

import java.awt.Color
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.*
import javax.swing.*
import kotlin.system.exitProcess


class SwingView(override var controller: Controller) : JFrame(), View {
    //  Initialization of the required strings and text fields.
    private lateinit var controlPanelText: JLabel
    private lateinit var controlPanelErrorText: JLabel
    private lateinit var mainFrame: JFrame
    private lateinit var controlPanel: JPanel
    private lateinit var fillPanelNotify: JLabel
    private lateinit var textEnter: JTextField
    private lateinit var fillPanel: JPanel
    private lateinit var infoLabel: JLabel
    private lateinit var infoFill: JLabel
    private lateinit var numWater: JTextField
    private lateinit var numMilk: JTextField
    private lateinit var numcofBeans: JTextField
    private lateinit var numCups: JTextField

    init {
        createUI()
    }

    //  Creates the main window.
    private fun createUI() {
        title = SwingView::class.java.toString()
        controlPanel = JPanel().apply { layout = FlowLayout() }
        fillPanel = JPanel().apply { layout = FlowLayout() }

        mainFrame = JFrame("Swing View").apply {
            setSize(700,600)
            layout = GridLayout(2,1)
            addWindowListener(object: WindowAdapter() {
                override fun windowClosing(e: WindowEvent?) {
                    exit()
                    exitProcess(0)
                }
            })
            add(controlPanel)
            add(fillPanel)
            isVisible = true
        }

        createControlPanel()
        createFillPanel()

        mainFrame.isVisible = true
    }

    //  Creation purchase and information panel.
    private fun createControlPanel() {
        infoLabel = JLabel("")
        controlPanel.add(infoLabel)

        controlPanelText = JLabel("<html><h2>Buy:</h3></html>")
        controlPanel.add(controlPanelText)

        textEnter = JTextField("1 - espresso, 2 - latte, 3 - cappuccino",10)//переделать
        controlPanel.add(textEnter)

        controlPanelErrorText = JLabel("")
        controlPanelErrorText.foreground = Color.red
        controlPanel.add(controlPanelErrorText)

        val makeButton = JButton("Make coffee").apply {
            addActionListener {
                val order = Order(textEnter.text.toString())
                controller.buy(order)
            }
        }

        controlPanel.add(makeButton)

        val takeButton = JButton("Take money and run").apply {
            addActionListener {
                controller.take()
            }
        }
        controlPanel.add(takeButton)
    }

    //  Create a Resource Fill Panel.
    private fun createFillPanel() {
        infoFill = JLabel("Write how many ml of water do you want to add:")
        numWater = JTextField("0",10)
        fillPanel.add(infoFill)
        fillPanel.add(numWater)

        infoFill = JLabel("Write how many ml of milk do you want to add:")
        fillPanel.add(infoFill)
        numMilk = JTextField("0",10)
        fillPanel.add(numMilk)

        infoFill = JLabel("Write how many grams of coffee beans do you want to add:")
        fillPanel.add(infoFill)
        numcofBeans = JTextField("0",10)
        fillPanel.add(numcofBeans)

        infoFill = JLabel("Write how many disposable cups of coffee do you want to add:")
        fillPanel.add(infoFill)
        numCups = JTextField("0",10)
        fillPanel.add(numCups)
        fillPanelNotify = JLabel("")
        fillPanelNotify.foreground = Color.green
        fillPanel.add(fillPanelNotify)
        val fillButton = JButton("Fill").apply {
            addActionListener {
                val resources = Resources.ChangeRes(water = numWater.text.toInt(),
                    milk = numMilk.text.toInt(),
                    coffeeBeans = numcofBeans.text.toInt(),
                    cups = numCups.text.toInt()
                )
                controller.fill(resources)
            }
        }
        fillPanel.add(fillButton)
        val backButton = JButton("Back").apply {
            addActionListener {
                mainFrame.isVisible = false
                begin()
            }
        }
        fillPanel.add(backButton)
    }

    override fun start() {
        var run = true
        var num = 0
        while (run) {
            controller.remaining()
            num += 1
            if (num == 100000000) {
                controlPanelErrorText.text = ""
                fillPanelNotify.text = ""
                num = 0
            }
            if (!mainFrame.isVisible) {
                run = false
            }
        }
    }

    override fun showInfo(info: String, label: Boolean): String {
        if (label) {
            fillPanelNotify.text = info
        } else {
            controlPanelErrorText.text = info
        }
        return "Displayed"
    }

    override fun showInfoRes(info: String): String {
        infoLabel.text = info
        return "Print"
    }

    override fun output(text: String) {}

    override fun exit(): Boolean {
        return false
    }
}