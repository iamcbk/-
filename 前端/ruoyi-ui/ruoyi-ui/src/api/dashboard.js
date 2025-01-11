import request from '@/utils/request'
import { listEmployee } from '@/api/employee/employee'

export function getDashboardStats() {
  return listEmployee({
    pageNum: 1,
    pageSize: 999
  }).then(res => {
    if (res.code === 200) {
      const employeeList = res.rows || []
      const totalSales = employeeList.reduce((sum, emp) => sum + (emp.salary || 0), 0)
      const totalOrders = employeeList.reduce((sum, emp) => sum + (emp.star || 0), 0)

      return {
        code: 200,
        data: {
          totalSales,
          totalOrders
        }
      }
    }
    return res
  })
}

export function getTopSellers() {
  return listEmployee({
    pageNum: 1,
    pageSize: 999,  // 获取所有员工数据
    orderByColumn: 'salary',  // 使用 salary 排序
    isAsc: 'desc'
  }).then(res => {
    if (res.code === 200) {
      const employeeList = res.rows || []

      if (employeeList.length === 0) {
        return {
          code: 200,
          data: []
        }
      }

      // 找出最高销售额
      const maxSales = Math.max(...employeeList.map(emp => Number(emp.salary) || 0))
      console.log('最高销售额:', maxSales) // 调试日志

      // 找出所有达到最高销售额的销售员
      const topSellers = employeeList
        .filter(emp => {
          const empSales = Number(emp.salary) || 0
          const isTop = empSales === maxSales
          console.log(`员工 ${emp.employeeName} 销售额: ${empSales}, 是否最高: ${isTop}`) // 调试日志
          return isTop
        })
        .map(emp => {
          // 计算排名和百分比
          const currentSales = Number(emp.salary) || 0
          const lowerCount = employeeList.filter(e => (Number(e.salary) || 0) < currentSales).length
          const percentage = Math.round((lowerCount / (employeeList.length - 1)) * 100)

          return {
            id: emp.employeeId,
            name: emp.employeeName,
            avatar: emp.employeeAvatar ? process.env.VUE_APP_BASE_API + emp.employeeAvatar : '',
            sales: currentSales,
            orders: Number(emp.star) || 0,
            percentage: percentage
          }
        })

      console.log('找到的销售冠军数量:', topSellers.length) // 调试日志
      console.log('销售冠军详情:', topSellers) // 调试日志

      return {
        code: 200,
        data: topSellers
      }
    }
    return res
  })
}

export function getSalesRanking() {
  return listEmployee({
    pageNum: 1,
    pageSize: 999,
    orderByColumn: 'salary',
    isAsc: 'desc'
  }).then(res => {
    if (res.code === 200) {
      const rankingList = (res.rows || []).map(emp => ({
        id: emp.employeeId,
        name: emp.employeeName,
        avatar: emp.employeeAvatar || '',
        sales: Number(emp.salary) || 0,
        orders: Number(emp.star) || 0
      }))

      return {
        code: 200,
        data: rankingList
      }
    }
    return res
  })
}
