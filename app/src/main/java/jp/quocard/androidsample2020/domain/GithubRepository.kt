package jp.quocard.androidsample2020.domain

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface GithubRepository {

    //中断関数でAPIにリクエストし、レスポンスをsuspend functionで受け取る(一覧)
    suspend fun getProjectList(userId: String): Response<List<Project>>


    //中断関数でAPIにリクエストし、レスポンスをsuspend functionで受け取る(詳細)
    suspend fun getProjectDetails(userID: String, projectName: String): Response<Project>


    //singletonでRepositoryインスタンスを返すFactory
    companion object Factory {

        const val HTTPS_API_GITHUB_URL = "https://api.github.com/"

        val instance: GithubRepository
            @Synchronized get() {
                return GithubRepositoryImpl()
            }
    }
}

class GithubRepositoryImpl: GithubRepository {

    //Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(GithubRepository.HTTPS_API_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var githubService: GithubService = retrofit.create(GithubService::class.java)


    //中断関数でAPIにリクエストし、レスポンスをsuspend functionで受け取る(一覧)
    override suspend fun getProjectList(userId: String): Response<List<Project>> =
        githubService.getProjectList(userId)


    //中断関数でAPIにリクエストし、レスポンスをsuspend functionで受け取る(詳細)
    override suspend fun getProjectDetails(userID: String, projectName: String): Response<Project> =
        githubService.getProjectDetails(userID, projectName)
}
