//
//  reviewclass.swift
//  ktMAB_OMI
//
//  Created by Kenny Trang on 10/6/21.
//

import Foundation

struct reviewlist {
    var title: String?
    var reviewDescription: String?
    var rating: String?
    init(titleName: String, review: String, ratingNum: String) {
        title = titleName
        reviewDescription = review
        rating = ratingNum
    }
}
//var reviews = reviewlist(titleName: "EXAMPLE TITLE", review: "Add your first review!", ratingNum: "10")



