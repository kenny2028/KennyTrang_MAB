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
    
    
    @IBOutlet weak var sizeLabel: UILabel!
    
    @IBAction func fontSizeSlider(_ sender: UISlider) {
        let _fontSize = sender.value
        sizeLabel.text = String(format: "%.0f", _fontSize)
        let _sizeCGFloat = CGFloat(_fontSize)
        artistLabel.font = UIFont.systemFont(ofSize: _sizeCGFloat)
        
    }
    
    
    

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }


}

