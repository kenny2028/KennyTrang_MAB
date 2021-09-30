//
//  ViewController.swift
//  toolbarLab
//
//  Created by Kenny Trang on 9/30/21.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var bookName: UILabel!
    
    @IBOutlet weak var bookAuthor: UILabel!
    
    var user = Favorite()
    
    
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    @IBAction func popoffSegue(_segue:UIStoryboardSegue){
        bookName.text = user.favBook
        bookAuthor.text = user.favAuthor
    }
    


}

