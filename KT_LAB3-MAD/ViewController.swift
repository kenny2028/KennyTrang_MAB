//
//  ViewController.swift
//  KT_LAB3-MAD
//
//  Created by Kenny Trang on 9/21/21.
//

import UIKit

class ViewController: UIViewController {
    

    
    @IBOutlet weak var artistImage: UIImageView!
    @IBOutlet weak var artistLabel: UILabel!
    @IBOutlet weak var capSwitch: UISwitch!
    var upper = false
    
    @IBAction func artistSegmentedControl(_ sender: UISegmentedControl) {
        let selected = sender.selectedSegmentIndex
        if selected == 0 {
            artistImage.image = UIImage(named: "sole")
            artistLabel.text = "Think - SOLE"
            updateText()
        } else {
            artistImage.image = UIImage(named: "meloh")
            artistLabel.text = "Smile - Meloh"
            updateText()
        }
    }
    
    func updateText() {
        if upper == true {
            artistLabel.text = artistLabel.text?.uppercased()
        } else {
            artistLabel.text = artistLabel.text?.lowercased()
        }
    }
            
    
    @IBAction func capSwitchCheck(_ sender: UISwitch) {
       if capSwitch.isOn {
           artistLabel.text = artistLabel.text?.uppercased()
           upper = true
       }else {
           artistLabel.text = artistLabel.text?.lowercased()
           upper = false
       }
    }
    
    

    @IBOutlet weak var sizeText: UILabel!
    

    @IBAction func sizeSlider(_ sender: UISlider) {
        let fontSize=sender.value //float
        sizeText.text=String(format: "%.0f", fontSize) //create a String from the float value
        let fontSizeCGFloat=CGFloat(fontSize) //create a CGFloat from a float
        artistLabel.font=UIFont.systemFont(ofSize: fontSizeCGFloat) //create a UIFont object and assign to the font property
    }
    
    

    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

