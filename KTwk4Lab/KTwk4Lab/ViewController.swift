//
//  ViewController.swift
//  KTwk4Lab
//
//  Created by Kenny Trang on 9/23/21.
//

import UIKit

//    Adopt TextfieldProtocol ()
class ViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var checkAmount: UITextField!
    
    @IBOutlet weak var tipPercent: UITextField!
    
    //    UITextField will pass when anytype of textfield has been entered
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
//    Needs a delegate therefore just using ViewController in viewDidLoad
    

    override func viewDidLoad() {
        checkAmount.delegate = self
        tipPercent.delegate = self
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    
    @IBOutlet weak var personLabel: UILabel!
    
    @IBOutlet weak var stepper: UIStepper!


    @IBAction func personStepper(_ sender: UIStepper) {
        if stepper.value == 1{
            personLabel.text = "1 Person"
        } else {
            personLabel.text = String(format: "%.0f", stepper.value) + " People"
        
        }
        updateTipTotals()
    }
    
    
    @IBOutlet weak var tipLabel: UILabel!
    
    @IBOutlet weak var totalLabel: UILabel!
    
    @IBOutlet weak var perPersonLabel: UILabel!
    
    func updateTipTotals() {
        var amount:Float
        var pct:Float
        
        if checkAmount.text!.isEmpty {
            amount = 0.0
        } else {
            amount = Float(checkAmount.text!)!
        }
        
        if tipPercent.text!.isEmpty {
            pct = 0.0
        } else {
            pct = Float(tipPercent.text!)!/100
        }
        
        let numberOfPeople = stepper.value
        let tip = amount * pct
        let total = amount + tip
        var personTotal : Float = 0.0
        if numberOfPeople > 0 {
            personTotal = total / Float(numberOfPeople)
        } else {
            let alert = UIAlertController(title: "Warning", message: "Number of people has to be greater than 0", preferredStyle: .alert)
            let cancelAction = UIAlertAction(title: "Cancel", style:UIAlertAction.Style.cancel)
            alert.addAction(cancelAction)
            let okAction = UIAlertAction(title: "Ok", style: UIAlertAction.Style.default)
            alert.addAction(okAction)
            
            self.present(alert, animated: true, completion: nil)
            
            personLabel.text = "1 Person"
            updateTipTotals()
            
            
        }
        
        let currencyFormatter = NumberFormatter()
        currencyFormatter.numberStyle = NumberFormatter.Style.currency
        
        tipLabel.text = currencyFormatter.string(from: NSNumber(value: tip))
        totalLabel.text = currencyFormatter.string(from: NSNumber(value: total))
        perPersonLabel.text = currencyFormatter.string(from: NSNumber(value: personTotal))
        
    }
    
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        updateTipTotals()
    }
    
    
    


}

