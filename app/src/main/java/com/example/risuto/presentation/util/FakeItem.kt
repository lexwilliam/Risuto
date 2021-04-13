package com.example.risuto.presentation.util

import com.example.risuto.data.remote.model.detail.*
import com.example.risuto.presentation.model.AnimePresentation
import com.example.risuto.presentation.model.AnimeListPresentation

fun generateFakeItemList(): List<AnimeListPresentation> {
    return listOf(
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS", "TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000),
        AnimeListPresentation(1, "", "Wonder Egg Priority", "NOW THAT IS POGGERS","TV", 7, 9.1f, 1000000)
    )
}

fun generateFakeItem(): AnimeListPresentation {
    return AnimeListPresentation(1, "", "Wonder Egg Priority", "Following the suicide of her best and only friend, Koito Nagase, Ai Ooto is left grappling with her new reality. With nothing left to live for, she follows the instructions of a mysterious entity and gets roped into purchasing an egg, or specifically, a Wonder Egg.\n" + "\n" + "Upon breaking the egg in a world that materializes during her sleep, Ai is tasked with saving people from the adversities that come their way. In doing so, she believes that she has moved one step closer to saving her best friend. With this dangerous yet tempting opportunity in the palms of her hands, Ai enters a place where she must recognize the relationship between other people's demons and her own.\n" + "\n" + "As past trauma, unforgettable regrets, and innate fears hatch in the bizarre world of Wonder Egg Priority, a young girl discovers the different inner struggles tormenting humankind and rescues them from their worst fears.\n" + "\n" + "[Written by MAL Rewrite]","TV", 7, 9.1f, 1000000)
}



fun generateFakeAnimeDetail(): AnimePresentation {
    return AnimePresentation(
        Aired("", Prop(From(0,0,0), To(0,0,0)),"", ""),
        false, "", "", "", listOf(""), 0,
        0, listOf(Genre(0,"", "", "")), "",
        listOf(Licensor(0, "", "", "")), 0,123123,
        listOf(""), 0, "", listOf(Producer(0, "","", "" )),
        0, "", Related(), 0, false, "", 10.0, 0,
        "", "", listOf(Studio(0, "", "", "")), "",
        "Wonder Egg Priority", "", "", listOf(""), "", "", "")
}