package com.lexwilliam.risuto.util

import com.lexwilliam.risuto.model.*

object FakeItems {
    val animeData: AnimePresentation.Data =
        AnimePresentation.Data(
            mal_id = 28851,
            url = "https://myanimelist.net/anime/28851/Koe_no_Katachi",
            images = AnimePresentation.Data.Images(
                jpg = AnimePresentation.Data.Images.Jpg(
                    image_url = "https://cdn.myanimelist.net/images/anime/1122/96435.jpg",
                    small_image_url = "https://cdn.myanimelist.net/images/anime/1122/96435t.jpg",
                    large_image_url = "https://cdn.myanimelist.net/images/anime/1122/96435l.jpg"
                ),
                webp = AnimePresentation.Data.Images.Webp(
                    image_url = "https://cdn.myanimelist.net/images/anime/1122/96435.webp",
                    small_image_url = "https://cdn.myanimelist.net/images/anime/1122/96435t.webp",
                    large_image_url = "https://cdn.myanimelist.net/images/anime/1122/96435l.webp"
                )
            ),
            trailer = AnimePresentation.Data.Trailer(
                youtube_id = "XBNWo25izJ8",
                url = "https://www.youtube.com/watch?v=XBNWo25izJ8",
                embed_url = "https://www.youtube.com/embed/XBNWo25izJ8?enablejsapi=1&wmode=opaque&autoplay=1",
            ),
            title = "Koe no Katachi",
            title_english = "A Silent Voice",
            title_japanese = "聲の形",
            title_synonyms = listOf("The Shape of Voice"),
            type = "Movie",
            source = "Manga",
            episodes = 1,
            status = "Finished Airing",
            airing = false,
            aired = AnimePresentation.Data.Aired(
                from = "2016-09-17T00:00:00+00:00",
                to = "",
                prop = AnimePresentation.Data.Aired.Prop(
                    from = AnimePresentation.Data.Aired.Prop.From(
                        day = 17,
                        month = 9,
                        year = 2016
                    ),
                    to = AnimePresentation.Data.Aired.Prop.To(
                        day = -1,
                        month = -1,
                        year = -1
                    ),
                    string = "Sep 17, 2016"
                )
            ),
            duration = "2 hr 10 min",
            rating = "PG-13 - Teens 13 or older",
            score = 8.96,
            scored_by = 1300653.0,
            rank = 14,
            popularity = 19,
            members = 1888865,
            favorites = 72824,
            synopsis = "As a wild youth, elementary school student Shouya Ishida sought to beat boredom in the cruelest ways. When the deaf Shouko Nishimiya transfers into his class, Shouya and the rest of his class thoughtlessly bully her for fun. However, when her mother notifies the school, he is singled out and blamed for everything done to her. With Shouko transferring out of the school, Shouya is left at the mercy of his classmates. He is heartlessly ostracized all throughout elementary and middle school, while teachers turn a blind eye. Now in his third year of high school, Shouya is still plagued by his wrongdoings as a young boy. Sincerely regretting his past actions, he sets out on a journey of redemption: to meet Shouko once more and make amends. Koe no Katachi tells the heartwarming tale of Shouya's reunion with Shouko and his honest attempts to redeem himself, all while being continually haunted by the shadows of his past. [Written by MAL Rewrite]",
            background = "Winner of the Excellence Award on the 20th Japan Media Arts Festival.",
            season = "",
            year = -1,
            broadcast = AnimePresentation.Data.Broadcast(
                day = "",
                time = "",
                timezone = "",
                string = ""
            ),
            producers = listOf(
                AnimePresentation.Data.Producer(
                    mal_id = 109,
                    type = "anime",
                    name = "Shochiku",
                    url = "https://myanimelist.net/anime/producer/109/Shochiku"
                )
            ),
            licensors = listOf(
                AnimePresentation.Data.Licensor(
                    mal_id = 531,
                    type = "anime",
                    name = "Eleven Arts",
                    url = "https://myanimelist.net/anime/producer/531/Eleven_Arts"
                )
            ),
            studios = listOf(
                AnimePresentation.Data.Studio(
                    mal_id = 2,
                    type = "anime",
                    name = "Kyoto Animation",
                    url = "https://myanimelist.net/anime/producer/2/Kyoto_Animation"
                )
            ),
            genres = listOf(
                AnimePresentation.Data.Genre(
                    mal_id = 8,
                    type = "anime",
                    name = "Drama",
                    url = "https://myanimelist.net/anime/genre/8/Drama"
                )
            ),
            explicit_genres = listOf(),
            themes = listOf(
                AnimePresentation.Data.Theme(
                    mal_id = 23,
                    type = "anime",
                    name = "School",
                    url = "https://myanimelist.net/anime/genre/23/School"
                )
            ),
            demographics = listOf(
                AnimePresentation.Data.Demographic(
                    mal_id = 27,
                    type = "anime",
                    name = "Shounen",
                    url = "https://myanimelist.net/anime/genre/27/Shounen"
                )
            )
        )
//    val animeDetail =
//        AnimeDetailPresentation(
//            alternative_titles = AnimeDetailPresentation.AlternativeTitles(
//                ""
//            )
//        )
    val animeDetail = AnimeDetailPresentation(
        id = 47778,
        title = "Kimetsu no Yaiba: Yuukaku-hen",
        main_picture = AnimeDetailPresentation.MainPicture(
            medium ="https://api-cdn.myanimelist.net/images/anime/1908/120036.jpg",
            large = "https://api-cdn.myanimelist.net/images/anime/1908/120036l.jpg"
        ),
        alternative_titles = AnimeDetailPresentation.AlternativeTitles(
            synonyms = listOf(),
            en = "Demon Slayer: Kimetsu no Yaiba Entertainment District Arc",
            ja = "鬼滅の刃 遊郭編"
        ),
        start_date = "2021-12-05",
        end_date = "2022-02-13",
        synopsis = "The devastation of the Mugen Train incident still weighs heavily on the members of the Demon Slayer Corps. Despite being given time to recover, life must go on, as the wicked never sleep: a vicious demon is terrorizing the alluring women of the Yoshiwara Entertainment District. The Sound Pillar, Tengen Uzui, and his three wives are on the case. However, when he soon loses contact with his spouses, Tengen fears the worst and enlists the help of Tanjirou Kamado, Zenitsu Agatsuma, and Inosuke Hashibira to infiltrate the district's most prominent houses and locate the depraved Upper Rank demon.\n\n[Written by MAL Rewrite]\n",
        mean = 8.91,
        rank = 20,
        popularity = 205,
        num_list_users = 707844,
        num_scoring_users = 325579,
        nsfw = "white",
        created_at = "2021-02-14T12:53:33+00:00",
        updated_at = "2022-03-06T08:43:02+00:00",
        media_type = "tv",
        status = "finished_airing",
        genres = listOf(
            AnimeDetailPresentation.Genre(
                id = 1,
                name = "Action"
            ),
            AnimeDetailPresentation.Genre(
                id = 2,
                name = "Adventure"
            ),
            AnimeDetailPresentation.Genre(
                id = 4,
                name = "Comedy"
            ),
            AnimeDetailPresentation.Genre(
                id = 6,
                name = "Demons"
            )
        ),
        num_episodes = 11,
        start_season = AnimeDetailPresentation.StartSeason(
            year = 2022,
            season = "winter"
        ),
        broadcast = AnimeDetailPresentation.Broadcast(
            day_of_the_week = "sunday",
            start_time = "23:15"
        ),
        source = "manga",
        average_episode_duration = 1500,
        rating = "r",
        studios = listOf(
            AnimeDetailPresentation.Studio(
                id = 43,
                name = "ufotable"
            )
        ),
        pictures = listOf(
            AnimeDetailPresentation.Picture(
                medium = "https://api-cdn.myanimelist.net/images/anime/1338/111945.jpg",
                large = "https://api-cdn.myanimelist.net/images/anime/1338/111945l.jpg"
            )
        ),
        background = "",
        related_anime = listOf(
            AnimeDetailPresentation.RelatedAnime(
                node = AnimeDetailPresentation.Node(
                    id = 40456,
                    title = "Kimetsu no Yaiba Movie: Mugen Ressha-hen",
                    main_picture = AnimeDetailPresentation.MainPicture(
                        medium = "https://api-cdn.myanimelist.net/images/anime/1704/106947.jpg",
                        large = "https://api-cdn.myanimelist.net/images/anime/1704/106947l.jpg"
                    )
                ),
                relation_type = "prequel",
                relation_type_formatted = "Prequel"
            )
        ),
        related_manga = listOf(),
        recommendations = listOf(
            AnimeDetailPresentation.Recommendation(
                node = AnimeDetailPresentation.Node(
                    id = 39617,
                    title = "Yakusoku no Neverland 2nd Season",
                    main_picture =  AnimeDetailPresentation.MainPicture(
                        medium = "https://api-cdn.myanimelist.net/images/anime/1815/110626.jpg",
                        large = "https://api-cdn.myanimelist.net/images/anime/1815/110626l.jpg"
                    )
                ),
                num_recommendations = 1
            )
        ),
        statistics = AnimeDetailPresentation.Statistics(
            status = AnimeDetailPresentation.Status(
                watching = "180310",
                completed = "344375",
                on_hold = "3846",
                dropped = "1182",
                plan_to_watch = "177893"
            ),
            num_list_users = 707606
        ),
        my_list_status = AnimeDetailPresentation.MyListStatus(
            is_rewatching = false,
            num_episodes_watched = 0,
            score = 0,
            status = "plan_to_watch",
            updated_at = ""
        )
    )

    val shortAnime = ShortAnimePresentation(
        mal_id = 0,
        image_url = "",
        title = "Wonder Egg Priority"
    )

    val character = AnimeCharactersPresentation.Data(
        character = AnimeCharactersPresentation.Data.Character(
            images = AnimeCharactersPresentation.Data.Character.Images(
                jpg = AnimeCharactersPresentation.Data.Character.Images.Jpg(
                    image_url = "",
                    small_image_url = ""
                ),
                webp = AnimeCharactersPresentation.Data.Character.Images.Webp(
                    image_url = "",
                    small_image_url = ""
                )
            ),
            mal_id = -1,
            name = "Takanashi Rie",
            url = ""
        ),
        role = "Main Character",
        voice_actors = listOf(
            AnimeCharactersPresentation.Data.VoiceActor(
                language = "Japanese",
                person = AnimeCharactersPresentation.Data.VoiceActor.Person(
                    images = AnimeCharactersPresentation.Data.VoiceActor.Person.Images(
                        jpg = AnimeCharactersPresentation.Data.VoiceActor.Person.Images.Jpg(
                            image_url = ""
                        )
                    ),
                    mal_id = -1,
                    name = "Megumin",
                    url = ""
                )
            )
        )
    )

    val fakeUserProfile =
        UserProfilePresentation.Data("","", emptyList(),
            UserProfilePresentation.Data.Favorites(emptyList(), emptyList(), emptyList()),"",
            UserProfilePresentation.Data.Images(UserProfilePresentation.Data.Images.Jpg("")),"","","",-1,
            UserProfilePresentation.Data.Statistics(UserProfilePresentation.Data.Statistics.Anime(-1,-1.0,-1,-1,-1.0,-1,-1,-1,-1,-1)),
            UserProfilePresentation.Data.Updates(emptyList()),"","")
}

