export const config = {
  apiGatewayUrl: 'http://localhost:8085/',
  projectService: '',
  skillApi: {
      getCategorySkills: 'http://localhost:8082/categories',
      getSkills: 'http://localhost:8082/skills',
  },
  projectApi: {
      getProject: 'http://localhost:8083/projects?ProjectId=0',
      getProjectByProjectId: 'http://localhost:8083/projects?ProjectId='
  },
  BidApi:{
    insertBid: "http://localhost:8084/bids"
  }
}