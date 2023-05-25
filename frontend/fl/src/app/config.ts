export const config = {
  apiGatewayUrl: 'http://localhost:8085/',
  projectService: '',
  skillApi: {
      getSkills: 'http://localhost:8082/skills',
      getCategorySkills: 'http://localhost:8082/categories'
  },
  projectApi: {
      getProject: 'http://localhost:8083/projects?ProjectId=0',
      getProjectByProjectId: 'http://localhost:8083/projects?ProjectId=',
      insertProject:'http://localhost:8083/projects'
  },
  BidService:'',
  BidApi:{
    insertBid: "http://localhost:8084/bids"
  },

  UserService:'',
  UserApi:{
    getLocation:"http://localhost:8081/users"
  }
}
