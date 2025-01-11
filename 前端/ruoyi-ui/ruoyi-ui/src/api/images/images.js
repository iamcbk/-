import request from '@/utils/request'

// 查询图片列表
export function listImages(query) {
  return request({
    url: '/images/images/list',
    method: 'get',
    params: query
  })
}

// 查询图片详细
export function getImages(otherId) {
  return request({
    url: '/images/images/' + otherId,
    method: 'get'
  })
}

// 新增图片
export function addImages(data) {
  return request({
    url: '/images/images',
    method: 'post',
    data: data
  })
}

// 修改图片
export function updateImages(data) {
  return request({
    url: '/images/images',
    method: 'put',
    data: data
  })
}

// 删除图片
export function delImages(otherId) {
  return request({
    url: '/images/images/' + otherId,
    method: 'delete'
  })
}
