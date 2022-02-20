package com.lexwilliam.risuto.mapper

import com.lexwilliam.data.model.remote.detail.*
import com.lexwilliam.domain.model.remote.detail.*
import com.lexwilliam.risuto.model.detail.*
import javax.inject.Inject

interface DetailMapper {
    fun toPresentation(anime: AnimeDetail): AnimeDetailPresentation
    fun toPresentation(charStaff: CharacterStaff): CharacterStaffPresentation
    fun toPresentation(episodes: Episodes): EpisodesPresentation
    fun toPresentation(forum: Forum): ForumPresentation
    fun toPresentation(moreInfo: MoreInfo): MoreInfoPresentation
    fun toPresentation(news: News): NewsPresentation
    fun toPresentation(pictures: Pictures): PicturesPresentation
    fun toPresentation(recommendations: Recommendations): RecommendationsPresentation
    fun toPresentation(reviews: Reviews): ReviewsPresentation
    fun toPresentation(stats: Stats): StatsPresentation
    fun toPresentation(videos: Videos): VideosPresentation
}

class DetailMapperImpl @Inject constructor(
    private val commonMapper: CommonMapper
): DetailMapper {
    override fun toPresentation(anime: AnimeDetail): AnimeDetailPresentation =
        AnimeDetailPresentation(commonMapper.toPresentation(anime.aired), anime.airing, anime.background, anime.broadcast, anime.duration, anime.ending_themes, anime.episodes, anime.favorites, anime.genres.map { commonMapper.toPresentation(it) }, anime.image_url, anime.licensors.map { commonMapper.toPresentation(it) }, anime.mal_id, anime.members, anime.opening_themes, anime.popularity, anime.premiered, anime.producers.map { commonMapper.toPresentation(it) }, anime.rank, anime.rating, commonMapper.toPresentation(anime.related), anime.request_cache_expiry, anime.request_cached, anime.request_hash, anime.score, anime.scored_by, anime.source, anime.status, anime.studios.map { commonMapper.toPresentation(it) }, anime.synopsis, anime.title, anime.title_english, anime.title_japanese, anime.title_synonyms, anime.trailer_url, anime.type, anime.url)

    override fun toPresentation(charStaff: CharacterStaff): CharacterStaffPresentation =
        CharacterStaffPresentation(charStaff.characters?.map { toPresentation(it) }, charStaff.staff?.map { toPresentation(it) })

    override fun toPresentation(episodes: Episodes): EpisodesPresentation =
        EpisodesPresentation(episodes.episodes.map { toPresentation(it) }, episodes.episodes_last_page)

    override fun toPresentation(forum: Forum): ForumPresentation =
        ForumPresentation(forum.topics.map { toPresentation(it) })

    override fun toPresentation(moreInfo: MoreInfo): MoreInfoPresentation =
        MoreInfoPresentation(moreInfo.moreinfo)

    override fun toPresentation(news: News): NewsPresentation =
        NewsPresentation(news.articles.map { toPresentation(it) })

    override fun toPresentation(pictures: Pictures): PicturesPresentation =
        PicturesPresentation(pictures.pictures.map { toPresentation(it) })

    override fun toPresentation(recommendations: Recommendations): RecommendationsPresentation =
        RecommendationsPresentation(recommendations.recommendations.map { toPresentation(it) })

    override fun toPresentation(reviews: Reviews): ReviewsPresentation =
        ReviewsPresentation(reviews.reviews.map { toPresentation(it) })

    override fun toPresentation(stats: Stats): StatsPresentation =
        StatsPresentation(stats.completed, stats.dropped, stats.on_hold, stats.plan_to_watch, toPresentation(stats.scores), stats.total, stats.watching)

    override fun toPresentation(videos: Videos): VideosPresentation =
        VideosPresentation(videos.episodes, videos.promo.map { toPresentation(it) })

    private fun toPresentation(character: Character): CharacterPresentation =
        CharacterPresentation(character.image_url, character.mal_id, character.name, character.role, character.url, character.voice_actors.map { toPresentation(it) })

    private fun toPresentation(voiceActor: VoiceActor): VoiceActorPresentation =
        VoiceActorPresentation(voiceActor.image_url, voiceActor.language, voiceActor.mal_id, voiceActor.name, voiceActor.url)

    private fun toPresentation(staff: Staff): StaffPresentation =
        StaffPresentation(staff.image_url, staff.mal_id, staff.name, staff.positions, staff.url)

    private fun toPresentation(episode: Episode): EpisodePresentation =
        EpisodePresentation(episode.aired, episode.episode_id, episode.filler, episode.forum_url, episode.recap, episode.title, episode.title_japanese, episode.title_romanji, episode.video_url)

    private fun toPresentation(topic: Topic): TopicPresentation =
        TopicPresentation(topic.author_name, topic.author_url, topic.date_posted, toPresentation(topic.last_post), topic.replies, topic.title, topic.topic_id, topic.url)

    private fun toPresentation(lastPost: LastPost): LastPostPresentation =
        LastPostPresentation(lastPost.author_name, lastPost.author_url, lastPost.date_posted, lastPost.url)

    private fun toPresentation(article: Article): ArticlePresentation =
        ArticlePresentation(article.author_name, article.author_url, article.comments, article.date, article.forum_url, article.image_url, article.intro, article.title, article.url)

    private fun toPresentation(picture: Picture): PicturePresentation =
        PicturePresentation(picture.large, picture.small)

    private fun toPresentation(recommendation: Recommendation): RecommendationPresentation =
        RecommendationPresentation(recommendation.image_url, recommendation.mal_id, recommendation.recommendation_count, recommendation.recommendation_url, recommendation.title, recommendation.url)

    private fun toPresentation(review: Review): ReviewPresentation =
        ReviewPresentation(review.content, review.date, review.helpful_count, review.mal_id, toPresentation(review.reviewer), review.type, review.url)

    private fun toPresentation(reviewer: Reviewer): ReviewerPresentation =
        ReviewerPresentation(reviewer.episodes_seen, reviewer.image_url, toPresentation(reviewer.reviewScore), reviewer.url, reviewer.username)

    private fun toPresentation(score: ReviewScore): ReviewScorePresentation =
        ReviewScorePresentation(score.animation, score.character, score.enjoyment, score.overall, score.sound, score.story)

    private fun toPresentation(promo: Promo): PromoPresentation =
        PromoPresentation(promo.image_url, promo.title, promo.video_url)

}