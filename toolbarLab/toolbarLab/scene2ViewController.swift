//
//  scene2ViewController.swift
//  toolbarLab
//
//  Created by Kenny Trang on 9/30/21.
//

import UIKit

class scene2ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var userBook: UITextField!
    @IBOutlet weak var userAuthor: UITextField!
    
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    override func viewDidLoad() {
        userBook.delegate = self
        userAuthor.delegate = self
        
        super.viewDidLoad()
//        Click off disable keyboard
        self.view.addGestureRecognizer(UITapGestureRecognizer(target: self.view, action: #selector(UIView.endEditing(_:))))

        // Do any additional setup after loading the view.
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier ==  "donefavs" {
            let scene1VC = segue.destination as! ViewController
            if userBook.text?.isEmpty == false && userAuthor.text?.isEmpty == false {
                scene1VC.user.favAuthor = userAuthor.text
                scene1VC.user.favBook = userBook.text
            }
        }
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
  
    
}
