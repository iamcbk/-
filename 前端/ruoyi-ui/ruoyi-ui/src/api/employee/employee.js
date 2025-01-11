import request from '@/utils/request'

// 查询员工业绩列表
export function listEmployee(query) {
  return request({
    url: '/employee/employee/list',
    method: 'get',
    params: query
  })
}

// 查询员工业绩详细
export function getEmployee(employeeId) {
  return request({
    url: '/employee/employee/' + employeeId,
    method: 'get'
  })
}

// 新增员工业绩
export function addEmployee(data) {
  return request({
    url: '/employee/employee',
    method: 'post',
    data: data
  })
}

// 修改员工业绩
export function updateEmployee(data) {
  return request({
    url: '/employee/employee',
    method: 'put',
    data: data
  })
}

// 删除员工业绩
export function delEmployee(employeeId) {
  return request({
    url: '/employee/employee/' + employeeId,
    method: 'delete'
  })
}
