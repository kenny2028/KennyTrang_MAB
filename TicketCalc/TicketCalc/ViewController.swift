//
//  ViewController.swift
//  TicketCalc
//
//  Created by Kenny Trang on 9/28/21.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var ticketField: UITextField!
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func viewDidLoad() {
        ticketField.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:))))
    }

    @IBOutlet weak var stepperButton: UIStepper!
    @IBOutlet weak var personnumLabel: UILabel!
    @IBAction func stepper(_ sender: UIStepper) {
        if stepperButton.value == 1 {
            personnumLabel.text = "1 Person"
        } else {
            personnumLabel.text = String(format: "%.0f", stepperButton.value) + " People"
        }
    }
    
    @IBOutlet weak var personCost: UILabel!
    
    @IBAction func calcButton(_ sender: UIButton) {
        if ticketField.text == "" {
//            IF TextField is empty
            let alert = UIAlertController(title: "Warning", message: "Please Enter a Ticket Price", preferredStyle: .alert)

            alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
            self.present(alert, animated: true)
            
        } else {
//            Do Calculation and Update Label
            doCalc()
            
            
        }
        
    }
    
    @IBOutlet weak var finalLabel: UILabel!
    func doCalc () {
        var ticketPrice:Float
        var numPeople:Float
        var finalCost:Float
        
        
        ticketPrice = Float(ticketField.text!)!
        numPeople = Float(stepperButton.value)
        
        finalCost = ticketPrice / numPeople
        
//        Update label

        
        finalLabel.text = String(format: "%.2f",finalCost) + "$"

        
        
        
    }
    
    
    
}

